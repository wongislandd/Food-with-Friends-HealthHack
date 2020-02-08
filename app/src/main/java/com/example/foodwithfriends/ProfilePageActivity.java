package com.example.foodwithfriends;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import org.w3c.dom.Text;

public class ProfilePageActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String data = "";
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                data = profile.getDisplayName();
            }
        }

        final TextView profile_name = findViewById(R.id.profile_name);
        final TextView profile_major = findViewById(R.id.profile_major);
        final TextView profile_pref = findViewById(R.id.profile_pref);
        final TextView profile_bio = findViewById(R.id.profile_bio);
        final ImageView profile_pic = findViewById(R.id.profile_picture);
        final Button friend_button = findViewById(R.id.friend_button);
        final Button check_button = findViewById(R.id.checkin_button);

        profile_name.setText(data);



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
