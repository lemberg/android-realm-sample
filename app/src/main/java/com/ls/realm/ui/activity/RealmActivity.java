package com.ls.realm.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import io.realm.Realm;

public class RealmActivity extends AppCompatActivity {

    protected Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null) {
            mRealm.close();
        }
    }
}
