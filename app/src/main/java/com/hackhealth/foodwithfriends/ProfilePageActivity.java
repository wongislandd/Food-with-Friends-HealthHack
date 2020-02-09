package com.hackhealth.foodwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfilePageActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private static final String TAG = "ProfilePageActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        final Button friend_button = findViewById(R.id.friend_button);
        final Button check_button = findViewById(R.id.checkin_button);

        DocumentReference docRef = db.collection("users").document(mAuth.getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        TextView profile_name = findViewById(R.id.profile_name);
                        TextView profile_major = findViewById(R.id.profile_major);
                        TextView profile_pref = findViewById(R.id.profile_pref);
                        TextView profile_bio = findViewById(R.id.profile_bio);
                        ImageView profile_pic = findViewById(R.id.profile_picture);
                        TextView profile_status = findViewById(R.id.profile_status);
                        profile_name.setText(document.getString("Name"));
                        profile_major.setText(document.getString("Major"));
                        profile_pref.setText(document.getString("Pref"));
                        profile_bio.setText(document.getString("Bio"));
                        profile_status.setText(document.getString("Status"));
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });



        friend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFriendsPage();
            }
        });
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCheckInpage();
            }
        });
    }

    public void goToFriendsPage(){
        Intent intent = new Intent(ProfilePageActivity.this, MyFriendsActivity.class);
        startActivity(intent);
    }
    public void goToCheckInpage() {
        Intent intent = new Intent(ProfilePageActivity.this, CheckInActivity.class);
        startActivity(intent);
    }
}
