package com.ls.realm.model.db;

import android.support.annotation.NonNull;
import com.ls.realm.model.db.dao.UserDao;
import com.ls.realm.model.db.data.User;

import io.realm.Realm;

public class RealmManager {

    public static UserDao createUserDao(@NonNull Realm realm) {
        return new UserDao(realm);
    }

    public static void clear(@NonNull Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.clear(User.class);
            }
        });
    }
}
