package com.epicodus.anonrec.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.models.Meeting;
import com.epicodus.anonrec.ui.MeetingDetailActivity;
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
public class MeetingListAdapter extends RecyclerView.Adapter<MeetingViewHolder>  {
    private DatabaseReference mRef;
    private ChildEventListener mChildEventListener;
    private ArrayList<Meeting> mMeetings = new ArrayList<>();
    private Context mContext;

    public MeetingListAdapter(Class<Meeting> modelClass, int modelLayout, Class<MeetingViewHolder> viewHolderClass, Query ref, Context context)  {
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
    protected void populateViewHolder(final MeetingViewHolder viewHolder, Meeting model, int position) {
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

    @Override
    public void onBindViewHolder(MeetingViewHolder holder, int position) {
        holder.bindMeeting(mMeetings.get(position));
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }
}
