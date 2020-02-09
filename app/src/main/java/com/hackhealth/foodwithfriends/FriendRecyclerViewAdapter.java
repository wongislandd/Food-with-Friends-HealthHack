package com.hackhealth.foodwithfriends;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class FriendRecyclerViewAdapter extends RecyclerView.Adapter<FriendRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "ItemRecyclerViewAdapter";
    private ArrayList<User> users;
    private Context mContext;
    public FriendRecyclerViewAdapter(Context ctx, ArrayList<User> users) {
        mContext = ctx;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.usersname.setText(users.get(position).getName());
        holder.userstatus.setText(users.get(position).getStatus());
        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                String id = users.get(position).getUserID();
                moveToProfile(id);
                return true;
            }
        });
    }

    public void moveToProfile(String id){
        Intent intent = new Intent(this.mContext,FriendsProfilePageActivity.class);
        Bundle b = new Bundle();
        b.putString("id", id); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView usersname;
        TextView userstatus;
        ConstraintLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usersname = itemView.findViewById(R.id.user_name);
            userstatus = itemView.findViewById(R.id.user_status);
            parentLayout = itemView.findViewById(R.id.parent_layout2);
        }
    }
}