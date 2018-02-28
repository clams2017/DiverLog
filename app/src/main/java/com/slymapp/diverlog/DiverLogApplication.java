package com.slymapp.diverlog;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.slymapp.diverlog.infrastructure.realm.RealmHelper;

public final class DiverLogApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        RealmHelper.initialize(this);
        TypefaceProvider.registerDefaultIconSets();
    }
}
