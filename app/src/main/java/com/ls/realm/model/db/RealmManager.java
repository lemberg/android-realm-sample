package com.ls.realm.model.db;

import com.ls.realm.model.db.dao.UserDao;

public class RealmManager {

    public static UserDao createUserDao() {
        return new UserDao();
    }
}
