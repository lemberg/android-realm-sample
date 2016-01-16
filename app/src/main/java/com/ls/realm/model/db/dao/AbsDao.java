package com.ls.realm.model.db.dao;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public abstract class AbsDao<T extends RealmObject> {

    private final Class<T> mClazz;
    protected final Realm mRealm;

    public AbsDao(Realm realm, Class<T> clazz) {
        this.mRealm = realm;
        this.mClazz = clazz;
    }

    public void save(final T obj) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(obj);
            }
        });
    }

    public void save(final List<T> objList) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(objList);
            }
        });
    }

    public RealmResults<T> loadAll() {
        return mRealm.where(mClazz).findAll();
    }

    public RealmResults<T> loadAllAsync() {
        return mRealm.where(mClazz).findAllAsync();
    }

    public void removeAll() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.clear(mClazz);
            }
        });
    }

    public long count() {
        return mRealm.where(mClazz).count();
    }
}
