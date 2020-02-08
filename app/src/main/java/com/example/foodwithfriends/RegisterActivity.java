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

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
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

                goToProfilePage(name.getText().toString(), major.getText().toString(), pref.getText().toString(), bio.getText().toString());
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




    public void goToProfilePage(String names, String majors, String prefs, String bios){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(names + "zz" + majors + "yy" + prefs + "xx" + bios + "ww" + "None")
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
        reloadProfilePage();
    }
    public void reloadProfilePage(){
        Intent intent = new Intent(RegisterActivity.this, ProfilePageActivity.class);
        startActivity(intent);
    }
}
