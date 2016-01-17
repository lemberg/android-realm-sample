package com.ls.realm.model.db;

import com.ls.realm.model.db.dao.UserDao;
import com.ls.realm.model.db.data.User;

import io.realm.Realm;

public class RealmManager {

    private static Realm mRealm;

    public static Realm open() {
        return mRealm = Realm.getDefaultInstance();
    }

    public static void close() {
        if (mRealm != null) {
            mRealm.close();
        }
    }

    public static void clear() {
        if (mRealm != null) {
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    mRealm.clear(User.class);
                }
            });
        }
    }

    public static UserDao createUserDao() {
        if (mRealm == null) {
            throw new IllegalStateException("RealmManager: Please call close() method first");
        }

        return new UserDao(mRealm);
    }
}
