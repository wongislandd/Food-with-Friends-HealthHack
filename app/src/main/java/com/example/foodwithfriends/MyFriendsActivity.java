package com.example.foodwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MyFriendsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_friends);
        getListItems();
    }

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MyFriendsActivity";
    ArrayList<User> users = new ArrayList<>();

    private void getListItems() {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                users.add(new User(document.getString("Bio"),document.getString("Major"),document.getString("Name"),document.getString("Pref"),
                                        document.getString("Status")));
                            }
                            initRecyclerView();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }



    public void goToProfile(View view) {
        Intent intent = new Intent(getApplicationContext(),ProfilePageActivity.class);
        startActivity(intent);
    }
    public void goToCheckIn(View view) {
        Intent intent = new Intent(getApplicationContext(),CheckInActivity.class);
        startActivity(intent);
    }
    public void myFriends(View view) {
        Intent intent = new Intent(getApplicationContext(),MyFriendsActivity.class);
        startActivity(intent);
    }
    private void initRecyclerView() { // same for all
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        FriendRecyclerViewAdapter adapter = new FriendRecyclerViewAdapter(this, users);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
