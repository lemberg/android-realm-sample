package com.ls.realm.model.db.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ls.realm.model.db.data.User;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

public class ContactDao {

    public void saveUser(@NonNull final Realm realm, @NonNull final User user) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                realm.copyToRealmOrUpdate(user);
            }
        });
    }

    public void saveUser(@NonNull final Realm realm, @NonNull final User user, @NonNull Realm.Transaction.Callback callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                realm.copyToRealmOrUpdate(user);
            }

        }, callback);
    }

    public void saveUserList(@NonNull final Realm realm, @NonNull final List<User> userList) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                realm.copyToRealmOrUpdate(userList);
            }
        });
    }

    public RealmAsyncTask saveUserList(@NonNull final Realm realm, @NonNull final List<User> userList, @NonNull Realm.Transaction.Callback callback) {
        return realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                realm.copyToRealmOrUpdate(userList);
            }

        }, callback);
    }

    @Nullable
    public User loadUser(@NonNull Realm realm, long id) {
        return realm.where(User.class).equalTo("id", id).findFirst();
    }

    @NonNull
    public List<User> loadUserList(@NonNull Realm realm) {
        return realm.where(User.class).findAll();
    }

    public RealmResults<User> loadUserListAsync(@NonNull Realm realm) {
        return realm.where(User.class).findAllAsync();
    }
}
