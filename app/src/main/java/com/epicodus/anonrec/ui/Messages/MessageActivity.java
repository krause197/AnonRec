package com.epicodus.anonrec.ui.messages;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.constants.MessageConstants;
import com.epicodus.anonrec.ui.general.HomePageActivity;
import com.epicodus.anonrec.ui.general.LoginActivity;
import com.epicodus.anonrec.ui.meetings.MeetingActivity;
import com.epicodus.anonrec.ui.meetups.MeetupActivity;
import com.epicodus.anonrec.ui.general.ProfileActivity;
import com.epicodus.anonrec.ui.meetups.SavedEventListActivity;
import com.epicodus.anonrec.util.ToastMessage;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener, ToastMessage {
    @Bind(R.id.discussionButton) Button mDiscussionButton;
    @Bind(R.id.newPostButton) Button mNewPostButton;
    @Bind(R.id.categoryParameter) Spinner mCategoryParameter;

    Spinner categoryParameter;
    Button discussionButton;
    String selectCategory;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        mDiscussionButton.setOnClickListener(this);
        mNewPostButton.setOnClickListener(this);

        categoryParameter = (Spinner) findViewById(R.id.categoryParameter);
        discussionButton = (Button) findViewById(R.id.discussionButton);

        categoryParameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int position, long id) {
                selectCategory = adapter.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView arg0) {

            }
        });

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
    }
    @Override
    public void getToast(){
        Toast.makeText(MessageActivity.this, toastMessages[randomMsgIndex], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        getToast();
        if (v == mDiscussionButton) {
            addCategoryToSharedPreferences(selectCategory);
            Intent intent = new Intent(MessageActivity.this, MessageListActivity.class);
            startActivity(intent);
        }
        if (v == mNewPostButton) {
            getToast();
            Intent intent = new Intent(MessageActivity.this, NewPostActivity.class);
            startActivity(intent);
        }
    }

    private void addCategoryToSharedPreferences(String selectCategory) {
        mEditor.putString(MessageConstants.FIREBASE_QUERY_CATEGORY, selectCategory).apply();
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
            getToast();
            Intent intent = new Intent(MessageActivity.this, HomePageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_profile) {
            getToast();
            Intent intent = new Intent(MessageActivity.this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_meetup) {
            getToast();
            Intent intent = new Intent(MessageActivity.this, MeetupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_meeting) {
            getToast();
            Intent intent = new Intent(MessageActivity.this, MeetingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_saved) {
            getToast();
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
