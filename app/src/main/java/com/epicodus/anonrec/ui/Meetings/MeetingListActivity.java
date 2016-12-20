package com.epicodus.anonrec.ui.meetings;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private SharedPreferences.Editor mEditor;
    private String mGroupName;
    private DatabaseReference mGroupReference;
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


        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {

        mMeetingReference = FirebaseDatabase.getInstance().getReference(MeetingConstants.FIREBASE_CHILD_MEETINGS).child(mRecentDay).child(mRecentRegion);

        Log.d(TAG, mMeetingReference + "");

        Query query = mMeetingReference.orderByChild("time");

        Log.d(TAG, query + "");

        mFirebaseAdapter = new MeetingListAdapter(Meeting.class, R.layout.meeting_list_item, MeetingViewHolder.class, query, this);


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        }

    private void groupFirebaseAdapter() {
        mGroupReference = FirebaseDatabase.getInstance().getReference(MeetingConstants.FIREBASE_CHILD_MEETINGS).child(mRecentDay);


        Query groupQuery = mGroupReference.orderByChild("group_name").startAt("#" + mGroupName);


        mFirebaseAdapter = new MeetingListAdapter(Meeting.class, R.layout.meeting_list_item, MeetingViewHolder.class, groupQuery, this);


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);


        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView groupName = (SearchView) MenuItemCompat.getActionView(menuItem);

        groupName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String mGroupName) {
                groupFirebaseAdapter();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
