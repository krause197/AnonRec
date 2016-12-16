package com.epicodus.anonrec.ui.meetups;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.epicodus.anonrec.services.MeetupService;
import com.epicodus.anonrec.R;
import com.epicodus.anonrec.adapters.meetups.EventListAdapter;
import com.epicodus.anonrec.models.Event;
import com.epicodus.anonrec.ui.general.HomePageActivity;
import com.epicodus.anonrec.ui.general.LoginActivity;
import com.epicodus.anonrec.ui.meetings.MeetingActivity;
import com.epicodus.anonrec.ui.messages.MessageActivity;
import com.epicodus.anonrec.ui.general.ProfileActivity;
import com.epicodus.anonrec.util.ToastMessage;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MeetupActivity extends AppCompatActivity implements ToastMessage {
    public static final String TAG = MeetupActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private EventListAdapter mEventAdapter;

    public ArrayList<Event> mEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetup);
        ButterKnife.bind(this);

        String groupName = "pdx-sober";

        getEvents(groupName);
    }

    private void getEvents(String groupName) {
        final MeetupService meetupService = new MeetupService();
        meetupService.findEvents(groupName, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mEvents = meetupService.processResults(response);

                MeetupActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mEventAdapter = new EventListAdapter(getApplicationContext(), mEvents);
                        mRecyclerView.setAdapter(mEventAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MeetupActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void getToast(){
        Toast.makeText(MeetupActivity.this, toastMessages[randomMsgIndex], Toast.LENGTH_LONG).show();
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
            Intent intent = new Intent(MeetupActivity.this, HomePageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_profile) {
            getToast();
            Intent intent = new Intent(MeetupActivity.this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_meeting) {
            getToast();
            Intent intent = new Intent(MeetupActivity.this, MeetingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_message) {
            getToast();
            Intent intent = new Intent(MeetupActivity.this, MessageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_saved) {
            getToast();
            Intent intent = new Intent(MeetupActivity.this, SavedEventListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MeetupActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
