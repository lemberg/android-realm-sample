package com.ls.realm.model.db.data;

import com.ls.realm.model.db.utils.RealmString;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements IViewType {

    @PrimaryKey
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    private RealmList<RealmString> contactList;

    public User() {
        contactList = new RealmList<>();
    }

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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RealmList<RealmString> getContactList() {
        return contactList;
    }

    public void setContactList(RealmList<RealmString> contactList) {
        this.contactList = contactList;
    }
}
