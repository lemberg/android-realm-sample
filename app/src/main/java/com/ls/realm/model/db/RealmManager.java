package com.ls.realm.model.db;

import com.ls.realm.model.db.dao.UserDao;
import com.ls.realm.model.db.data.User;

import io.realm.Realm;

public class RealmManager {

    private static Realm mRealm;

    public static Realm open() {
        mRealm = Realm.getDefaultInstance();
        return mRealm;
    }

    public static void close() {
        if (mRealm != null) {
            mRealm.close();
        }
    }

    public static UserDao createUserDao() {
        checkForOpenRealm();
        return new UserDao(mRealm);
    }

    public static void clear() {
        checkForOpenRealm();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.clear(User.class);
            }
        });
    }

    private static void checkForOpenRealm() {
        if (mRealm == null) {
            throw new IllegalStateException("RealmManager: Realm is closed, call open() method first");
        }
    }
}
