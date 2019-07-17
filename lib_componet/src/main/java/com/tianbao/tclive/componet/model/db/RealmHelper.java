package com.tianbao.tclive.componet.model.db;
import javax.inject.Inject;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by codeest on 16/8/16.
 */

public class RealmHelper implements DBHelper {

    private static final String DB_NAME = "myRealm.realm";

    private Realm mRealm;

    @Inject
    public RealmHelper() {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }

}
