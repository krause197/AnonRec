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

import butterknife.ButterKnife;


public class MeetingListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private String mUserRegion;
    private DatabaseReference mRegionReference;
    private String mRecentDay;
    private String mRecentRegion;
    public static final String TAG = MeetingListActivity.class.getSimpleName();
    private DatabaseReference mMeetingReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private FirebaseRecyclerAdapter mNewFirebaseAdapter;
    private String regionTitle;
    private TextView mRegionTitle;
    private RecyclerView mRecyclerView;
    private Menu menu;
//    private ListView mMeetingListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        mRegionTitle = (TextView) findViewById(R.id.regionTitle);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        mMeetingListView = (ListView) findViewById(R.id.meetingListView);

        regionTitle = getIntent().getExtras().getString("regionTitle");

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

//    private void manualFirebaseAdapter() {
//        mRegionReference = FirebaseDatabase.getInstance().getReference(MeetingConstants.FIREBASE_CHILD_MEETINGS).child(mRecentDay).child(mUserRegion);
//
//
//        Query regionQuery = mRegionReference.orderByChild("time");
//
//
//        mNewFirebaseAdapter = new MeetingListAdapter(Meeting.class, R.layout.meeting_list_item, MeetingViewHolder.class, regionQuery, this);
//
//
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mNewFirebaseAdapter);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        this.menu = menu;




        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView userRegion = (SearchView) MenuItemCompat.getActionView(menuItem);
        userRegion.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                final String searchRegion = userRegion.getQuery().toString();
                mRecentRegion = searchRegion.replaceAll("[^A-Za-z]+", "").toLowerCase();


                mRegionTitle.setText(searchRegion);


                setUpFirebaseAdapter();

                mRecyclerView.setAdapter(mFirebaseAdapter);
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
