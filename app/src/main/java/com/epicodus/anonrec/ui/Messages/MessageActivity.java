package com.epicodus.anonrec.ui.messages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.ui.general.HomePageActivity;
import com.epicodus.anonrec.ui.general.LoginActivity;
import com.epicodus.anonrec.ui.meetings.MeetingActivity;
import com.epicodus.anonrec.ui.meetups.MeetupActivity;
import com.epicodus.anonrec.ui.general.ProfileActivity;
import com.epicodus.anonrec.ui.meetups.SavedEventListActivity;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.discussionButton) Button mDiscussionButton;
    @Bind(R.id.newPostButton) Button mNewPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        mDiscussionButton.setOnClickListener(this);
        mNewPostButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MessageActivity.this, "Accept the Things You Cannot Change", Toast.LENGTH_LONG).show();
        if (v == mDiscussionButton) {
            Intent intent = new Intent(MessageActivity.this, MessageCategoryActivity.class);
            startActivity(intent);
        }
        if (v == mNewPostButton) {
            Intent intent = new Intent(MessageActivity.this, NewPostActivity.class);
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
        if (id == R.id.action_home) {
            Intent intent = new Intent(MessageActivity.this, HomePageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_profile) {
            Intent intent = new Intent(MessageActivity.this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_meetup) {
            Intent intent = new Intent(MessageActivity.this, MeetupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_meeting) {
            Intent intent = new Intent(MessageActivity.this, MeetingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_saved) {
            Intent intent = new Intent(MessageActivity.this, SavedEventListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MessageActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
