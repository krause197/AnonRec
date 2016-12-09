package com.epicodus.droid_anonrec_week1.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.droid_anonrec_week1.R;
import com.google.firebase.auth.FirebaseAuth;

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
