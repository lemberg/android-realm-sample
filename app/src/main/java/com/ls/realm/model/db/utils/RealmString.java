package com.ls.realm.model.db.utils;

import io.realm.RealmObject;

public class RealmString extends RealmObject {

    private String string;

    public RealmString() {}

    public RealmString(String str) {
        string = str;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
