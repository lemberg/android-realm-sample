package com.ls.realm.model.db.data;

import com.ls.realm.model.db.utils.RealmString;

import java.util.List;

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

    public static boolean equals(List<User> userList1, List<User> userList2) {
        boolean isEquals = true;

        if (userList1 != null && userList2 != null && userList1.size() == userList2.size()) {
            for (int i = 0; i < userList1.size(); i++) {
                User user1 = userList1.get(i);
                User user2 = userList2.get(i);

                if (!equals(user1, user2)) {
                    isEquals = false;
                    break;
                }
            }
        } else {
            isEquals = false;
        }

        return isEquals;
    }

    public static boolean equals(User user1, User user2) {
        if (user1.getId() != user2.getId()) return false;

        if (user1.getAge() != user2.getAge()) return false;

        if (user1.getFirstName() == null || user2.getFirstName() == null || !user1.getFirstName().equals(user2.getFirstName()))
            return false;

        if (user1.getLastName() == null || user2.getLastName() == null || !user1.getLastName().equals(user2.getLastName()))
            return false;

        if (user1.getEmail() == null || user2.getEmail() == null || !user1.getEmail().equals(user2.getEmail()))
            return false;

        if (user1.getContactList() != null && user2.getContactList() != null && user1.getContactList().size() == user2.getContactList().size()) {
            for (int i = 0; i < user1.getContactList().size(); i++) {
                String contact1 = user1.getContactList().get(i).getString();
                String contact2 = user2.getContactList().get(i).getString();

                if (!contact1.equals(contact2)) {
                    return false;
                }
            }
        }

        return true;
    }
}
