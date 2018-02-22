package com.example.miodragmilosevic.roomtest;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;

/**
 * Created by miodrag.milosevic on 1/25/2018.
 */

public class EpilepsyTrackerDebugApplication extends EpilepsyTrackerApplication {
    private static final String TAG = "Miki";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: stetho init");
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        );
    }

}
