package com.example.foodwithfriends;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final Button izan = findViewById((R.id.izan));
        final Button hajime = findViewById((R.id.hajime));
        final Button matt = findViewById((R.id.matt));

        izan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToCheckInPage();
            }
        });
        matt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfilePage();
            }
        });

        hajime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: yeet");
                goToFriendsPage();
            }
        });



        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
    }


    public void login(String email, String password){

    }


    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }



    public void goToFriendsPage(){
        Intent intent = new Intent(LoginActivity.this, MyFriendsActivity.class);
        startActivity(intent);
    }
    public void goToProfilePage(){
        Intent intent = new Intent(LoginActivity.this, ProfilePageActivity.class);
        startActivity(intent);
    }
    public void goToCheckInPage(){
        Intent intent = new Intent(LoginActivity.this, CheckInActivity.class);
        startActivity(intent);
    }
}
