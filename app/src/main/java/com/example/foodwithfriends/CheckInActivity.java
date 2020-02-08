package com.example.foodwithfriends;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CheckInActivity extends AppCompatActivity {

    Switch switch1;
    //Boolean switchState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_in);

        //switch1 = this.findViewById(R.id.switch1);
        //switchState = switch1.isChecked();
        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.soho);
        switch1 = this.findViewById(R.id.switch1);
        switch1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
                //mp.start();
            }
        }
        );



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