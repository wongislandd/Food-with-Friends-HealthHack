package com.example.foodwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
