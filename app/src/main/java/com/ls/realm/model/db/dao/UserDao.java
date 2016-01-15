package com.ls.realm.model.db.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ls.realm.model.db.data.User;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class UserDao {

    public void saveUser(@NonNull final Realm realm, @NonNull final User user) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealmOrUpdate(user);
            }
        });
    }

    public void saveUser(@NonNull final Realm realm, @NonNull final User user, @NonNull Realm.Transaction.Callback callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealmOrUpdate(user);
            }

        }, callback);
    }

    public void saveUserList(@NonNull final Realm realm, @NonNull final List<User> userList) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealmOrUpdate(userList);
            }
        });
    }

    public RealmAsyncTask saveUserList(@NonNull final Realm realm, @NonNull final List<User> userList, @NonNull Realm.Transaction.Callback callback) {
        return realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealmOrUpdate(userList);
            }

        }, callback);
    }

    @Nullable
    public RealmObject loadUser(@NonNull Realm realm, final long id) {
        return realm.where(User.class).equalTo("id", id).findFirst();
    }

    @NonNull
    public RealmResults<User> loadUserList(@NonNull Realm realm) {
        return realm.where(User.class).findAll();
    }

    public RealmResults<User> loadUserListAsync(@NonNull Realm realm) {
        return realm.where(User.class).findAllAsync();
    }
}
