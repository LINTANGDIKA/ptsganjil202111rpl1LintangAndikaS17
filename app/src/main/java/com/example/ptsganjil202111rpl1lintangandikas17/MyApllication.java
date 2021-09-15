package com.example.ptsganjil202111rpl1lintangandikas17;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApllication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("sportdb1")
                .schemaVersion(0).allowWritesOnUiThread(true)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

}
