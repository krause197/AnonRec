package com.epicodus.anonrec.adapters.meetings;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.anonrec.constants.MeetingConstants;
import com.epicodus.anonrec.R;
import com.epicodus.anonrec.models.Meeting;
import com.epicodus.anonrec.ui.meetings.MeetingDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 12/14/16.
 */
public class MeetingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public MeetingViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);

    }

    public void bindMeeting (Meeting meeting) {
        TextView groupnameTextView = (TextView) mView.findViewById(R.id.groupnameTextView);
        TextView dayTextView = (TextView) mView.findViewById(R.id.dayTextView);
        TextView timeTextView = (TextView) mView.findViewById(R.id.timeTextView);

        groupnameTextView.setText(meeting.getGroupname());
        dayTextView.setText(meeting.getDay());
        timeTextView.setText(meeting.getTime());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Meeting> meetings = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(MeetingConstants.FIREBASE_CHILD_MEETINGS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    meetings.add(snapshot.getValue(Meeting.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, MeetingDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("meetings", Parcels.wrap(meetings));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
