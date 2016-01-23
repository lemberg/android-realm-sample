package com.ls.realm.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ls.realm.R;
import com.ls.realm.model.db.RealmManager;
import com.ls.realm.model.db.data.User;
import com.ls.realm.model.db.utils.DataGenerator;
import com.ls.realm.ui.adapter.RealmAdapter;

import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class HomeActivity extends AppCompatActivity {

    private RealmAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_home);
        RealmManager.open();

        initViews();
        saveUserList();
        loadUserListAsync();
    }

    @Override
    protected void onDestroy() {
        RealmManager.close();
        super.onDestroy();
    }

    private void initViews() {
        mAdapter = new RealmAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void saveUserList() {
        RealmManager.createUserDao().save(DataGenerator.generateUserList());
    }

    private void loadUserListAsync() {
        final RealmResults<User> dataList = RealmManager.createUserDao().loadAllAsync();
        dataList.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                updateRecyclerView(dataList);
            }
        });
    }

    private void updateRecyclerView(List<User> userList) {
        if (mAdapter != null && userList != null) {
            mAdapter.setData(userList);
            mAdapter.notifyDataSetChanged();
        }
    }
}
