package com.epicodus.anonrec.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.anonrec.MeetingConstants;
import com.epicodus.anonrec.R;
import com.epicodus.anonrec.adapters.MeetingViewHolder;
import com.epicodus.anonrec.models.Meeting;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MeetingListActivity extends AppCompatActivity {
    private DatabaseReference mMeetingReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private DatabaseReference m


    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        ButterKnife.bind(this);

        mMeetingReference = FirebaseDatabase.getInstance().getReference(MeetingConstants.FIREBASE_CHILD_MEETINGS);
        m
        setUpFirebaseAdaper();
    }

    private void setUpFirebaseAdaper() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Meeting, MeetingViewHolder>(Meeting.class, R.layout.activity_meeting_list, MeeingViewHolder.class, mMeetingReference) {


            @Override
            protected void populateViewHolder(MeetingViewHolder viewHolder, Meeting model, int position) {
                viewHolder.bindMeeting(model);

                }
            };

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
