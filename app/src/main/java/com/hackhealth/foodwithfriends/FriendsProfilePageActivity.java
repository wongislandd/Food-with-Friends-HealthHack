package com.hackhealth.foodwithfriends;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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

public class FriendsProfilePageActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "FriendsProfilePage";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        Bundle b = getIntent().getExtras();
        String id = ""; // or other values
        if (b != null) {
            id = b.getString("id");
            DocumentReference docRef = db.collection("users").document(id);
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
        }
    }
}
