package com.epicodus.anonrec.ui.meetups;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.epicodus.anonrec.constants.MeetupConstants;
import com.epicodus.anonrec.R;
import com.epicodus.anonrec.adapters.SavedEventViewHolder;
import com.epicodus.anonrec.models.Event;
import com.epicodus.anonrec.ui.general.HomePageActivity;
import com.epicodus.anonrec.ui.general.LoginActivity;
import com.epicodus.anonrec.ui.general.ProfileActivity;
import com.epicodus.anonrec.ui.meetings.MeetingActivity;
import com.epicodus.anonrec.ui.messages.MessageActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedEventListActivity extends AppCompatActivity {
    private DatabaseReference mEventReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meetup);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mEventReference = FirebaseDatabase.getInstance().getReference(MeetupConstants.FIREBASE_CHILD_EVENTS).child(uid);
        setupFirebaseAdapter();
    }

    private void setupFirebaseAdapter() {

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Event, SavedEventViewHolder>
                (Event.class, R.layout.event_list_item, SavedEventViewHolder.class, mEventReference) {
            @Override
            protected void populateViewHolder(SavedEventViewHolder viewHolder, Event model, int position) {
                viewHolder.bindEvent(model);
            }
        };

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
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
            Intent intent = new Intent(SavedEventListActivity.this, HomePageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_message) {
            Intent intent = new Intent(SavedEventListActivity.this, MessageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_meetup) {
            Intent intent = new Intent(SavedEventListActivity.this, MeetupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_meeting) {
            Intent intent = new Intent(SavedEventListActivity.this, MeetingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_profile) {
            Intent intent = new Intent(SavedEventListActivity.this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(SavedEventListActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
