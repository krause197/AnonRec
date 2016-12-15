package com.epicodus.anonrec.ui.meetings;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.epicodus.anonrec.constants.MeetingConstants;
import com.epicodus.anonrec.R;
import com.epicodus.anonrec.adapters.meetings.MeetingListAdapter;
import com.epicodus.anonrec.adapters.meetings.MeetingViewHolder;
import com.epicodus.anonrec.models.Meeting;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MeetingListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private String mRecentDay;
    private String mRecentRegion;
    public static final String TAG = MeetingListActivity.class.getSimpleName();
    private DatabaseReference mMeetingReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private String regionTitle;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.regionTitle) TextView mRegionTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        regionTitle = getIntent().getExtras().getString("regionTitle");
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentDay = mSharedPreferences.getString(MeetingConstants.FIREBASE_QUERY_DAY, null);
        Log.d("Shared Pref Day", mRecentDay);
        mRecentRegion = mSharedPreferences.getString(MeetingConstants.FIREBASE_QUERY_REGION, null);
        Log.d("Shared Pref Region", mRecentRegion);

        mRegionTitle.setText(regionTitle);


        setUpFirebaseAdaper();
    }

    private void setUpFirebaseAdaper() {

        mMeetingReference = FirebaseDatabase.getInstance().getReference(MeetingConstants.FIREBASE_CHILD_MEETINGS).child(mRecentDay).child(mRecentRegion);

        Log.d(TAG, mMeetingReference + "");

        Query query = mMeetingReference.orderByChild("time");

        Log.d(TAG, query + "");

        mFirebaseAdapter = new MeetingListAdapter(Meeting.class, R.layout.meeting_list_item, MeetingViewHolder.class, query, this);


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
