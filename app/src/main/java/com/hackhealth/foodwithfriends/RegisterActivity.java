package com.hackhealth.foodwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        final Button Complete = findViewById(R.id.complete);
        final EditText name = findViewById(R.id.text_name);
        final EditText major = findViewById(R.id.text_major);
        final EditText pref = findViewById(R.id.text_pref);
        final EditText bio = findViewById(R.id.text_bio);

        Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> user = new HashMap<>();
                user.put("Name", name.getText().toString());
                user.put("Major", major.getText().toString());
                user.put("Pref", pref.getText().toString());
                user.put("Bio", bio.getText().toString());
                user.put("Status", "Nowhere");
                user.put("Id",mAuth.getCurrentUser().getUid());
                // Write a message to the database
                db.collection("users")
                        .document(mAuth.getCurrentUser().getUid()).set(user
                );
                goToProfilePage();
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
        name.addTextChangedListener(afterTextChangedListener);
        major.addTextChangedListener(afterTextChangedListener);
        pref.addTextChangedListener(afterTextChangedListener);
        bio.addTextChangedListener(afterTextChangedListener);

    }




    public void goToProfilePage(){
        Intent intent = new Intent(RegisterActivity.this, ProfilePageActivity.class);
        startActivity(intent);
    }
}
