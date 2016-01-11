package com.rd.testproject.model.db.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rd.testproject.model.db.data.User;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
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

    public void saveUserList(@NonNull final Realm realm, @NonNull final List<User> userList, @NonNull Realm.Transaction.Callback callback) {
        realm.executeTransaction(new Realm.Transaction() {
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
        List<User> userList = new ArrayList<>();
        userList.addAll(realm.where(User.class).findAll());
        return userList;
    }

    public RealmResults<User> loadUserListAsync(@NonNull Realm realm) {
        return realm.where(User.class).findAllAsync();
    }
}
