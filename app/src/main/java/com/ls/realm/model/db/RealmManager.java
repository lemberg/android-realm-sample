package com.ls.realm.model.db;

import com.ls.realm.model.db.dao.ContactDao;

public class RealmManager {

    public static ContactDao createEmployeeDao() {
        return new ContactDao();
    }
}
