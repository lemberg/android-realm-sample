package com.ls.realm.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ls.realm.R;
import com.ls.realm.model.db.RealmManager;
import com.ls.realm.model.db.data.User;
import com.ls.realm.model.db.utils.ObjectGenerator;
import com.ls.realm.ui.adapter.RealmAdapter;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class HomeActivity extends AppCompatActivity {

    private RealmResults<User> mDataList;
    private RealmAdapter mAdapter;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_home);
        mRealm = Realm.getDefaultInstance();

        initViews();

        long count = mRealm.where(User.class).count();
        if (count == 0) {
            saveUserList(mRealm);
        } else {
            loadUserListAsync(mRealm);
        }
    }

    @Override
    protected void onDestroy() {
        if (mDataList != null) {
            mDataList.removeChangeListeners();
        }

        if (mRealm != null) {
            mRealm.close();
        }
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

    private void saveUserList(@NonNull Realm realm) {
        RealmManager.createUserDao().saveUserList(realm, ObjectGenerator.generateUserList(), new Realm.Transaction.Callback() {
            @Override
            public void onSuccess() {
                loadUserListAsync(mRealm);
            }

            @Override
            public void onError(Exception e) {
            }
        });
    }

    private void loadUserListAsync(@NonNull Realm realm) {
        mDataList = RealmManager.createUserDao().loadUserListAsync(realm);
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
