package com.ls.realm.model.db.utils;

import android.support.annotation.NonNull;

import com.ls.realm.model.db.data.User;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class DataGenerator {

    @NonNull
    public static List<User> generateUserList() {
        List<User> userList = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            User user = new User();
            user.setId(i);
            user.setFirstName("John" + i);
            user.setLastName("Doe" + i);
            user.setAge(i + 20);
            user.setEmail(String.format("JohnDoe%d@gmail.com", i));
            user.getContactList().addAll(generateContactList());

            userList.add(user);
        }

        return userList;
    }

    public static User generateUser() {
        User user = new User();
        user.setId(1);
        user.setAge(22);
        user.setEmail("user@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setContactList(generateContactList());
        return user;
    }

    private static RealmList<RealmString> generateContactList() {
        RealmList<RealmString> contactList = new RealmList<>();
        for (int i = 0; i < 3; i++) {
            contactList.add(new RealmString("Contact " + i));
        }
        return contactList;
    }
}
