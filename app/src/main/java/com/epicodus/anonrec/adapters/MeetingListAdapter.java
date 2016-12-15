package com.epicodus.anonrec.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.epicodus.anonrec.models.Meeting;
import com.epicodus.anonrec.ui.meetings.MeetingDetailActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;


import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 12/14/16.
 */
public class MeetingListAdapter extends FirebaseRecyclerAdapter<Meeting, MeetingViewHolder> {
    private DatabaseReference mRef;
    private ChildEventListener mChildEventListener;
    private ArrayList<Meeting> mMeetings = new ArrayList<>();
    private Context mContext;

    public MeetingListAdapter(Class<Meeting> modelClass, int modelLayout, Class<MeetingViewHolder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mMeetings.add(dataSnapshot.getValue(Meeting.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


        @Override
        protected void populateViewHolder(final MeetingViewHolder viewHolder, Meeting model, int position){
            viewHolder.bindMeeting(model);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MeetingDetailActivity.class);
                    intent.putExtra("position", viewHolder.getAdapterPosition());
                    intent.putExtra("meetings", Parcels.wrap(mMeetings));
                    mContext.startActivity(intent);
                }
            });
        }

}
