package com.kangbakso.fdbrlayer.controller;

import com.apple.foundationdb.record.RecordMetaData;
import com.apple.foundationdb.record.RecordMetaDataBuilder;
import com.apple.foundationdb.record.metadata.Key;
import com.apple.foundationdb.record.metadata.RecordType;
import com.apple.foundationdb.record.provider.foundationdb.FDBMetaDataStore;
import com.apple.foundationdb.record.provider.foundationdb.FDBRecordContext;
import com.apple.foundationdb.record.provider.foundationdb.keyspace.DirectoryLayerDirectory;
import com.apple.foundationdb.record.provider.foundationdb.keyspace.KeySpace;
import com.apple.foundationdb.record.provider.foundationdb.keyspace.KeySpaceDirectory;
import com.apple.foundationdb.record.provider.foundationdb.keyspace.KeySpacePath;
import com.github.os72.protobuf.dynamic.DynamicSchema;
import com.github.os72.protobuf.dynamic.MessageDefinition;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.kangbakso.fdbrlayer.db.FdbRLayerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static com.apple.foundationdb.record.metadata.Key.Expressions.concatenateFields;

@RestController
public class FdbRLayerController {
    Logger logger = LoggerFactory.getLogger(FdbRLayerController.class);

    @Autowired
    FdbRLayerProvider fdbRLayerProvider;

    public static final String APPLICATION_NAME = "record-store";
    public static final KeySpace RS_KEY_SPACE =
            new KeySpace(
                    new DirectoryLayerDirectory("application")
                            .addSubdirectory(new KeySpaceDirectory("tenant", KeySpaceDirectory.KeyType.STRING)
                                    .addSubdirectory(new KeySpaceDirectory("recordSpace", KeySpaceDirectory.KeyType.STRING)
                                            .addSubdirectory(new KeySpaceDirectory("metadata", KeySpaceDirectory.KeyType.STRING, "m"))
                                            .addSubdirectory(new KeySpaceDirectory("data", KeySpaceDirectory.KeyType.STRING, "d"))
                                    )));

    public static KeySpacePath getMetaDataKeySpacePath(String tenant, String recordSpace) {
        return getKeySpacePath(tenant, recordSpace, "metadata");
    }

    public static KeySpacePath getApplicationKeySpacePath(String tenant) {
        return RS_KEY_SPACE
                .path("application", APPLICATION_NAME)
                .add("tenant", tenant);
    }

    public static KeySpacePath getDataKeySpacePath(String tenant, String recordSpace) {
        return getKeySpacePath(tenant, recordSpace, "data");
    }

    private static KeySpacePath getKeySpacePath(String tenant, String env, String subDirectory) {
        return RS_KEY_SPACE
                .path("application", APPLICATION_NAME)
                .add("tenant", tenant)
                .add("recordSpace", env)
                .add(subDirectory);
    }

    public static FDBMetaDataStore createMetadataStore(FDBRecordContext context, String tenant, String env) {
        FDBMetaDataStore metaDataStore = new FDBMetaDataStore(context, getMetaDataKeySpacePath(tenant, env));
        metaDataStore.setMaintainHistory(true);
        context.commit();
        return metaDataStore;
    }

    @GetMapping("/addMetadata")
    public ResponseEntity addMetadata(@RequestParam String appName) {
        String tenantID = "brodog";
        String recordSpace = "develop";

        try {
            FDBRecordContext context = fdbRLayerProvider.db.openContext(Collections.singletonMap("tenant", tenantID), null);
            FDBMetaDataStore metaDataStore = createMetadataStore(context, tenantID, recordSpace);
            DynamicSchema.Builder schemaBuilder = DynamicSchema.newBuilder();
            schemaBuilder.setName("NewSchema.proto");

            MessageDefinition msgDef = MessageDefinition.newBuilder("Person") // message Person
                    .addField("required", "int32", "id", 1)		// required int32 id = 1
                    .addField("required", "string", "name", 2)	// required string name = 2
                    .addField("optional", "string", "email", 3)	// optional string email = 3
                    .build();

            MessageDefinition unionDef = MessageDefinition.newBuilder("RecordTypeUnion") // message Person
                    .addField("optional", "Person", "_Person", 1)		// required int32 id = 1
                    .build();

            schemaBuilder.addMessageDefinition(msgDef);
            schemaBuilder.addMessageDefinition(unionDef);
            DynamicSchema schema = schemaBuilder.build();

            RecordMetaData newRecordMetaData = createRecordMetaData(schema.getFileDescriptorSet(), 1);
            metaDataStore.saveRecordMetaData(newRecordMetaData.getRecordMetaData().toProto());
            context.commit();

            return new ResponseEntity<Object>(appName, new HttpHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }
    private RecordMetaData createRecordMetaData(DescriptorProtos.FileDescriptorSet schema, int version) throws Descriptors.DescriptorValidationException {

        // retrieving protobuf descriptor
        RecordMetaDataBuilder metadataBuilder = RecordMetaData.newBuilder();

        for (DescriptorProtos.FileDescriptorProto fdp : schema.getFileList()) {
            Descriptors.FileDescriptor fd = Descriptors.FileDescriptor.buildFrom(fdp, new Descriptors.FileDescriptor[]{});
            // updating schema
            metadataBuilder.setRecords(fd);
        }

        // set options
        metadataBuilder.setVersion(version);
        metadataBuilder.setStoreRecordVersions(true);
        metadataBuilder.setSplitLongRecords(true);
//        metadataBuilder.getRecordType("Person")
//                .setPrimaryKey(concatenateFields("id", "name"));
        metadataBuilder.getRecordType("Person").setPrimaryKey(Key.Expressions.field("id"));

        return metadataBuilder.build();
    }

    @GetMapping("/getMetadata")
    public ResponseEntity getMetadata() {
        String tenantID = "brodog";
        String recordSpace = "develop";
        RecordMetaData schema = getSchema(tenantID, recordSpace);
        String recordTypes = schema.getRecordTypes().toString();
        RecordType recordType = schema.getRecordType("Person");


        return new ResponseEntity<Object>(recordTypes, new HttpHeaders(), HttpStatus.ACCEPTED);
    }

    public RecordMetaData getSchema(String tenantID, String recordSpace) {
        FDBRecordContext context = fdbRLayerProvider.db.openContext(Collections.singletonMap("tenant", tenantID), null);
        FDBMetaDataStore metaDataStore = createMetadataStore(context, tenantID, recordSpace);

        return metaDataStore.getRecordMetaData();
    }
}
