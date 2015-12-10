package com.rd.testproject.model.db.data;

import com.rd.testproject.model.db.utils.RealmString;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private RealmList<RealmString> contactList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public RealmList<RealmString> getContactList() {
        return contactList;
    }

    public void setContactList(RealmList<RealmString> contactList) {
        this.contactList = contactList;
    }
}
