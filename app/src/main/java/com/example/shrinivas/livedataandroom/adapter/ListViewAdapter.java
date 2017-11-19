package com.example.shrinivas.livedataandroom.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shrinivas.livedataandroom.R;
import com.example.shrinivas.livedataandroom.dto.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.MovieHolder> {

    private List<UserInfo> userInfos;
    private Context mContext;

    public ListViewAdapter(Context mContext) {
        this.userInfos = new ArrayList<>();
        this.mContext = mContext;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_child_element, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        UserInfo userInfo = userInfos.get(position);
        holder.userName.setText(userInfo.getUserName());
        holder.userEmailid.setText(userInfo.getUserEmail());
    }

    @Override
    public int getItemCount() {
        return userInfos.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {

        LinearLayout moviesLayout;
        TextView userName;
        TextView userEmailid;

        public MovieHolder(View itemView) {
            super(itemView);
            moviesLayout = (LinearLayout) itemView.findViewById(R.id.movies_child_layout);
            userName = (TextView) itemView.findViewById(R.id.userName);
            userEmailid = (TextView) itemView.findViewById(R.id.emailId);


        }
    }
}