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

    // TODO アプリ起動時にログを全削除する。ログ削除機能実装後に削除予定
    public static void reset(Context context) {
        Realm.init(context);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .schemaVersion(0L)
                .migration(new RealmMigration() {
                    @Override
                    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

                    }
                })
                .build();
        Realm.deleteRealm(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);
    }
}
