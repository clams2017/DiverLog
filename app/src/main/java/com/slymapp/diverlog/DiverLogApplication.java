package com.slymapp.diverlog;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public final class DiverLogApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
    }
}
