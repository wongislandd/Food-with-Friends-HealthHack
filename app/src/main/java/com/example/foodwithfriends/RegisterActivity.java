package com.example.foodwithfriends;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
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
                // Write a message to the database
                db.collection("users")
                        .document(mAuth.getCurrentUser().getUid()).set(user
                );
                //goToProfilePage();
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
