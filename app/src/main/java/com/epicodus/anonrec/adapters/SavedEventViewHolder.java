package com.epicodus.anonrec.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.anonrec.MeetupConstants;
import com.epicodus.anonrec.R;
import com.epicodus.anonrec.models.Event;
import com.epicodus.anonrec.ui.MeetupDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 12/13/16.
 */
public class SavedEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public SavedEventViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindEvent(Event event) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.nameTextView);
        TextView timeTextView = (TextView) mView.findViewById(R.id.timeTextView);
        TextView group_nameTextView = (TextView) mView.findViewById(R.id.group_nameTextView);

        nameTextView.setText(event.getName());
        timeTextView.setText("Date and Time: " + event.getDateTimeGroup());
        group_nameTextView.setText("MeetUp Group Name: " + event.getGroup_name());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Event> events = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(MeetupConstants.FIREBASE_CHILD_EVENTS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    events.add(snapshot.getValue(Event.class));
                }
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, MeetupDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("events", Parcels.wrap(events));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError){

            }

        });
    }
}
