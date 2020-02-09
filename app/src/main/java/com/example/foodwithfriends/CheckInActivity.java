package com.example.foodwithfriends;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CheckInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Switch switch1;
    //Boolean switchState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_in);
        mAuth = FirebaseAuth.getInstance();

        //switch1 = this.findViewById(R.id.switch1);
        //switchState = switch1.isChecked();
        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.soho);
        final Switch switch1 = this.findViewById(R.id.switch1);
        final Switch switch2 = this.findViewById(R.id.switch2);
        final Switch switch3 = this.findViewById(R.id.switch3);
        final Switch switch4 = this.findViewById(R.id.switch4);
        final Switch switch5 = this.findViewById(R.id.switch5);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ((switch2.isChecked() == false) && (switch3.isChecked() == false) && (switch4.isChecked() == false) && (switch5.isChecked() == false)) {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Status", "At East Side Dining");
                    db.collection("users")
                            .document(mAuth.getCurrentUser().getUid()).update(user);
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