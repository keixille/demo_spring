package com.kangbakso.demofdbrlayer.controller;

import com.apple.foundationdb.record.RecordMetaData;
import com.apple.foundationdb.record.RecordMetaDataBuilder;
import com.apple.foundationdb.record.metadata.Key;
import com.apple.foundationdb.record.provider.foundationdb.FDBMetaDataStore;
import com.apple.foundationdb.record.provider.foundationdb.FDBRecordContext;
import com.apple.foundationdb.record.provider.foundationdb.FDBRecordStore;
import com.apple.foundationdb.record.provider.foundationdb.keyspace.DirectoryLayerDirectory;
import com.apple.foundationdb.record.provider.foundationdb.keyspace.KeySpace;
import com.apple.foundationdb.record.provider.foundationdb.keyspace.KeySpaceDirectory;
import com.apple.foundationdb.record.provider.foundationdb.keyspace.KeySpacePath;
import com.google.protobuf.Descriptors;
import com.kangbakso.demofdbrlayer.SampleProto;
import com.kangbakso.demofdbrlayer.db.FdbRLayerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class FdbRLayerController {
    Logger logger = LoggerFactory.getLogger(FdbRLayerController.class);

    @Autowired
    FdbRLayerProvider fdbRLayerProvider;

    @GetMapping("/addMetadata")
    public ResponseEntity addMetadata(@RequestParam String appName) {
        final KeySpace appKeySpace = new KeySpace(
                new DirectoryLayerDirectory("application")
        );
        final KeySpacePath appPath = appKeySpace.path("application", appName);

        try {
            RecordMetaDataBuilder metaDataBuilder = RecordMetaData.newBuilder()
                    .setRecords(SampleProto.getDescriptor());

            fdbRLayerProvider.db.run((FDBRecordContext ctx) -> {
                FDBMetaDataStore fdbMetaDataStore = new FDBMetaDataStore(ctx, appPath);
                metaDataBuilder.getRecordType("Vendor")
                        .setPrimaryKey(Key.Expressions.field("vendor_id"));
                fdbMetaDataStore.saveRecordMetaData(metaDataBuilder);

//                FDBRecordStore.Builder recordStoreBuilder = FDBRecordStore.newBuilder()
//                        .setMetaDataProvider(rmd)
//                        .setKeySpacePath(appPath);
//                FDBRecordStore store = recordStoreBuilder.copyBuilder().setContext(ctx).create();
//                store.saveRecord(SampleProto.Vendor.newBuilder()
//                        .setVendorId(9375L)
//                        .setVendorName("Acme")
//                        .build());
                return null;
            });

            return new ResponseEntity<Object>(appName, new HttpHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/simpleInsert")
    public ResponseEntity simpleInsert(@RequestParam String appName, @RequestParam String env) {
        // Metadata Keyspace
        final KeySpace appKeySpace = new KeySpace(
                new DirectoryLayerDirectory("application")
        );
        final KeySpacePath appPath = appKeySpace.path("application", appName);

        // Record Keyspace
        final KeySpace recordKeySpace = new KeySpace(
                new DirectoryLayerDirectory("application")
                        .addSubdirectory(new KeySpaceDirectory("environment", KeySpaceDirectory.KeyType.STRING))
        );
        final KeySpacePath recordPath = recordKeySpace.path("application", appName)
                .add("environment", env);

        try {
            fdbRLayerProvider.db.run((FDBRecordContext ctx) -> {
                FDBMetaDataStore fdbMetaDataStore = new FDBMetaDataStore(ctx, appPath);
                RecordMetaData rmd = fdbMetaDataStore.getRecordMetaData();

                FDBRecordStore store = FDBRecordStore.newBuilder()
                        .setMetaDataProvider(rmd)
                        .setContext(ctx)
                        .setKeySpacePath(appPath)
                        .createOrOpen();;
                store.saveRecord(SampleProto.Vendor.newBuilder()
                        .setVendorId(9375L)
                        .setVendorName("Acme")
                        .build());
                return null;
            });
            return new ResponseEntity<Object>("success", new HttpHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }
}
