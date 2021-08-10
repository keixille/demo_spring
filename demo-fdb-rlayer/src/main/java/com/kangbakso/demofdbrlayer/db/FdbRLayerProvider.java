package com.kangbakso.demofdbrlayer.db;

import com.apple.foundationdb.record.provider.foundationdb.FDBDatabase;
import com.apple.foundationdb.record.provider.foundationdb.FDBDatabaseFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FdbRLayerProvider {
    public static final FDBDatabase db = FDBDatabaseFactory.instance().getDatabase();
}
