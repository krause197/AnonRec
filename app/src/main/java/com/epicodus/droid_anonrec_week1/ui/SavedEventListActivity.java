package com.epicodus.droid_anonrec_week1.ui;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.droid_anonrec_week1.MeetupConstants;
import com.epicodus.droid_anonrec_week1.R;
import com.epicodus.droid_anonrec_week1.adapters.SavedEventViewHolder;
import com.epicodus.droid_anonrec_week1.models.Event;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
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

        mEventReference = FirebaseDatabase.getInstance().getReference(MeetupConstants.FIREBASE_CHILD_EVENTS);
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
}
