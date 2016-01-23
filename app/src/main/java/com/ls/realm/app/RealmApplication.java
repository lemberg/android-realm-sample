package com.ls.realm.app;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder(this)
                        .name("realm-sample.db")
                        .schemaVersion(1)
                        .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
