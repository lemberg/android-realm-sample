package com.ls.realm.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ls.realm.R;
import com.ls.realm.model.db.RealmManager;
import com.ls.realm.model.db.data.User;
import com.ls.realm.model.db.utils.Generator;
import com.ls.realm.ui.adapter.RealmAdapter;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class HomeActivity extends AppCompatActivity {

    private RealmResults<User> mDataList;
    private RealmAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_home);
        RealmManager.open();

        initViews();
        loadData();
    }

    @Override
    protected void onDestroy() {
        if (mDataList != null) {
            mDataList.removeChangeListeners();
        }

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

    private void loadData() {
        long count = RealmManager.createUserDao().count();
        if (count == 0) {
            saveUserList();
        } else {
            loadUserListAsync();
        }
    }

    private void saveUserList() {
        RealmManager.createUserDao().save(Generator.generateUserList());
    }

    private void loadUserListAsync() {
        mDataList = RealmManager.createUserDao().loadAllAsync();
        mDataList.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                updateRecyclerView();
            }
        });
    }

    private void updateRecyclerView() {
        if (mAdapter != null && mDataList != null) {
            if (mAdapter.getItemCount() == 0) {
                mAdapter.setData(mDataList);
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}
