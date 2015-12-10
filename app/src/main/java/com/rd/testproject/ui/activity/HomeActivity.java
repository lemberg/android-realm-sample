package com.rd.testproject.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.rd.testproject.R;
import com.rd.testproject.model.db.RealmManager;
import com.rd.testproject.model.db.data.User;
import com.rd.testproject.model.db.utils.RealmString;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

public class HomeActivity extends AppCompatActivity {

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRealm = Realm.getDefaultInstance();

        saveUserList();
        loadUserList();
    }

    @Override
    protected void onDestroy() {
        if (mRealm != null) {
            mRealm.close();
        }
        super.onDestroy();
    }

    private void saveUserList() {
        if (mRealm != null) {
            List<User> userList = generateUserList();
            RealmManager.createEmployeeDao().saveUserList(mRealm, userList);
        }
    }

    private void loadUserListAsync() {
        if (mRealm == null) {
            return;
        }

        final RealmResults<User> result = RealmManager.createEmployeeDao().loadUserListAsync(mRealm);
        result.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                List<User> userList = new ArrayList<>();
                userList.addAll(result);
                //update UI
            }
        });
    }

    private  List<User> loadUserList() {
        if (mRealm == null) {
            return new ArrayList<>();
        }

        List<User> userList = new ArrayList<>();
        userList.addAll(RealmManager.createEmployeeDao().loadUserList(mRealm));

        return userList;
    }

    @NonNull
    private List<User> generateUserList() {
        List<User> userList = new ArrayList<>();
        RealmList<RealmString> contactList = generateContactList();

        for (int i = 1; i <= 1000; i++) {
            User user = new User();
            user.setId(i);
            user.setFirstName("First name " + i);
            user.setLastName("Last name " + i);
            user.setAge(i + 20);
            user.setContactList(contactList);

            userList.add(user);
        }

        return userList;
    }

    private RealmList<RealmString> generateContactList() {
        RealmList<RealmString> contactList = new RealmList<>();
        for (int i = 0; i < 3; i++) {
            contactList.add(new RealmString("Contact " + i));
        }
        return contactList;
    }
}
