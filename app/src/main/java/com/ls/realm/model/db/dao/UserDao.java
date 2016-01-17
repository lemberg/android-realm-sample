package com.ls.realm.model.db.dao;

import android.support.annotation.Nullable;

import com.ls.realm.model.db.data.User;

import io.realm.Realm;
import io.realm.RealmObject;

public class UserDao extends AbsDao<User> {

    public UserDao(Realm realm) {
        super(realm, User.class);
    }

    public RealmObject loadBy(long id) {
        return mRealm.where(User.class).equalTo("id", id).findFirst();
    }

    public void remove(@Nullable final RealmObject object) {
        if(object != null) {
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    object.removeFromRealm();
                }
            });
        }
    }
}
