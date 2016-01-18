package com.ls.realm.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ls.realm.R;
import com.ls.realm.model.db.data.IViewType;
import com.ls.realm.model.db.data.User;

import java.util.ArrayList;
import java.util.List;

public class RealmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TYPE_USER = 1;

    private List<IViewType> mDataList;

    public RealmAdapter() {
        mDataList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE_USER:
                View confessionView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
                return new UserViewHolder(confessionView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_TYPE_USER) {
            initUser(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        IViewType type = mDataList.get(position);
        if (type instanceof User) {
            return ITEM_TYPE_USER;
        }

        return 0;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setData(@NonNull List<? extends IViewType> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
    }

    private void initUser(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserViewHolder viewHolder = (UserViewHolder) holder;
        User user = (User) mDataList.get(position);

        viewHolder.txtFirstName.setText(user.getFirstName());
        viewHolder.txtLastName.setText(user.getLastName());
        viewHolder.txtAge.setText(String.format("%d yo", user.getAge()));
        viewHolder.txtEmail.setText(user.getEmail());
        viewHolder.txtCount.setText(String.format("Contacts count: %d", user.getContactList().size()));
    }

    private static class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView txtFirstName;
        private TextView txtLastName;
        private TextView txtAge;
        private TextView txtEmail;
        private TextView txtCount;

        public UserViewHolder(View v) {
            super(v);

            txtFirstName = (TextView) v.findViewById(R.id.txtFirstName);
            txtLastName = (TextView) v.findViewById(R.id.txtLastName);
            txtAge = (TextView) v.findViewById(R.id.txtAge);
            txtEmail = (TextView) v.findViewById(R.id.txtEmail);
            txtCount = (TextView) v.findViewById(R.id.txtContactsCount);
        }
    }
}
