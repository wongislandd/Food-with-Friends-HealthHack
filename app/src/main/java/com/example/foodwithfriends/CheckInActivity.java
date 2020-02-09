package com.example.foodwithfriends;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CheckInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "CheckInActivity";

    Switch switch1;
    //Boolean switchState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_in);
        mAuth = FirebaseAuth.getInstance();
        DocumentReference docRef = db.collection("users").document(mAuth.getCurrentUser().getUid());

        //switch1 = this.findViewById(R.id.switch1);
        //switchState = switch1.isChecked();
        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.soho);
        final Switch switch1 = this.findViewById(R.id.switch1);
        final Switch switch2 = this.findViewById(R.id.switch2);
        final Switch switch3 = this.findViewById(R.id.switch3);
        final Switch switch4 = this.findViewById(R.id.switch4);
        final Switch switch5 = this.findViewById(R.id.switch5);
        final Button clear = this.findViewById(R.id.clear);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        String location = document.getString("Status");
                        if (location.equals("AtEast Side Dining"))
                            switch1.setChecked(true);
                        if (location.equals("At West Side Dining"))
                            switch2.setChecked(true);
                        if (location.equals("At Jasmine"))
                            switch3.setChecked(true);
                        if (location.equals("At Roth Cafe"))
                            switch4.setChecked(true);
                        if (location.equals("At SAC Food Court"))
                            switch5.setChecked(true);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch1.setChecked(false);
                switch2.setChecked(false);
                switch3.setChecked(false);
                switch4.setChecked(false);
                switch5.setChecked(false);
                Map<String, Object> user = new HashMap<>();
                user.put("Status", "Away");
                db.collection("users")
                        .document(mAuth.getCurrentUser().getUid()).update(user);
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ((switch2.isChecked() == false) && (switch3.isChecked() == false) && (switch4.isChecked() == false) && (switch5.isChecked() == false)) {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Status", "At East Side Dining");
                    db.collection("users")
                            .document(mAuth.getCurrentUser().getUid()).update(user);
                }
                else {
                    switch1.setChecked(false);
                }
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ((switch1.isChecked() == false) && (switch3.isChecked() == false) && (switch4.isChecked() == false) && (switch5.isChecked() == false)) {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Status", "At West Side Dining");
                    db.collection("users")
                            .document(mAuth.getCurrentUser().getUid()).update(user
                    );
                }
                else {
                    switch2.setChecked(false);
                }
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ((switch2.isChecked() == false) && (switch1.isChecked() == false) && (switch4.isChecked() == false) && (switch5.isChecked() == false)) {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Status", "At Jasmine");
                    db.collection("users")
                            .document(mAuth.getCurrentUser().getUid()).update(user
                    );
                }
                else {
                    switch3.setChecked(false);
                }
            }
        });
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ((switch2.isChecked() == false) && (switch3.isChecked() == false) && (switch1.isChecked() == false) && (switch5.isChecked() == false)) {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Status", "At Roth Cafe");
                    db.collection("users")
                            .document(mAuth.getCurrentUser().getUid()).update(user
                    );
                }
                else {
                    switch4.setChecked(false);
                }
            }
        });
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ((switch2.isChecked() == false) && (switch3.isChecked() == false) && (switch4.isChecked() == false) && (switch1.isChecked() == false)) {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Status", "At SAC Food Court");
                    db.collection("users")
                            .document(mAuth.getCurrentUser().getUid()).update(user
                    );
                }
                else {
                    switch5.setChecked(false);
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
}