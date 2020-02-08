package com.example.foodwithfriends;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ProfilePageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        final TextView profile_name = findViewById(R.id.profile_name);
        final TextView profile_major = findViewById(R.id.profile_major);
        final TextView profile_pref = findViewById(R.id.profile_pref);
        final TextView profile_bio = findViewById(R.id.profile_bio);
        final ImageView profile_pic = findViewById(R.id.profile_picture);

        profile_name.setText("Test: Hello World");


    }
}
