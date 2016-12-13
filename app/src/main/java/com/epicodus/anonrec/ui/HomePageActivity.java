package com.epicodus.anonrec.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.epicodus.anonrec.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.meetingButton) Button mMeetingButton;
    @Bind(R.id.meetupButton) Button mMeetupButton;
    @Bind(R.id.messageButton) Button mMessageButton;
    @Bind(R.id.savedMeetupButton) Button mSavedMeetupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);

        mMeetingButton.setOnClickListener(this);
        mMeetupButton.setOnClickListener(this);
        mMessageButton.setOnClickListener(this);
        mSavedMeetupButton.setOnClickListener(this);
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
            startActivity(intent);
        }
        if (v == mSavedMeetupButton) {
            Intent intent = new Intent(HomePageActivity.this, SavedEventListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        if (id == R.id.action_meeting) {
            Intent intent = new Intent(HomePageActivity.this, MeetingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_profile) {
            Intent intent = new Intent(HomePageActivity.this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_meetup) {
            Intent intent = new Intent(HomePageActivity.this, MeetupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_message) {
            Intent intent = new Intent(HomePageActivity.this, MessageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_saved) {
            Intent intent = new Intent(HomePageActivity.this, SavedEventListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
