package com.rd.testproject.model.db;

import com.rd.testproject.model.db.dao.ContactDao;

public class RealmManager {

    public static ContactDao createEmployeeDao() {
        return new ContactDao();
    }
}
