package com.slymapp.diverlog.infrastructure.realm;

import android.content.Context;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

/**
 *
 */
public class RealmHelper {

    public static void initialize(Context context) {
        Realm.init(context);
        Realm.setDefaultConfiguration(
                new RealmConfiguration.Builder()
                        .schemaVersion(0L)
                        .migration(new RealmMigration() {
                            @Override
                            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

                            }
                        })
                        .build()
        );
    }
}
