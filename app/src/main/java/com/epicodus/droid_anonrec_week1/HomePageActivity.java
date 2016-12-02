package com.epicodus.droid_anonrec_week1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.name) TextView mName;
    @Bind(R.id.meetingButton) Button mMeetingButton;
    @Bind(R.id.meetupButton) Button mMeetupButton;
    @Bind(R.id.messageButton) Button mMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mName.setText(name);
        mMeetingButton.setOnClickListener(this);
        mMeetupButton.setOnClickListener(this);
        mMessageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mMeetingButton) {
            Intent intent = new Intent(HomePageActivity.this, MeetingActivity.class);
            startActivity(intent);
        }
        if (v == mMeetupButton) {
            Intent intent = new Intent(HomePageActivity.this, MeetupActivity.class);
            startActivity(intent);
        }
        if (v == mMessageButton) {
            Intent intent = new Intent(HomePageActivity.this, MessageActivity.class);
        }
    }
}
