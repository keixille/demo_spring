package com.kangbakso.demofdbjava.db;

import com.apple.foundationdb.Database;
import com.apple.foundationdb.FDB;
import com.apple.foundationdb.subspace.Subspace;
import com.apple.foundationdb.tuple.Tuple;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FdbProvider {
    private static final FDB fdb;
    public static final Database db;
    public static final Subspace userSubspace;

    static {
        fdb = FDB.selectAPIVersion(630);
        db = fdb.open();
        userSubspace = new Subspace(Tuple.from("user_subspace"));
    }
}
