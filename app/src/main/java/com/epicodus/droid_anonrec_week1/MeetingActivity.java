package com.epicodus.droid_anonrec_week1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;

public class MeetingActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.backButton) Button mbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        mbackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mbackButton) {
            Intent intent = new Intent(MeetingActivity.this, HomePageActivity.class);
            startActivity(intent);
        }
    }
}