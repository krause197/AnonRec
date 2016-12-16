package com.epicodus.anonrec.adapters.meetups;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.anonrec.constants.MeetupConstants;
import com.epicodus.anonrec.R;
import com.epicodus.anonrec.models.Event;
import com.epicodus.anonrec.ui.meetups.MeetupDetailActivity;
import com.epicodus.anonrec.util.ItemTouchHelperViewHolder;
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
public class SavedEventViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    View mView;
    Context mContext;
    public ImageView mSoberPDXIcon;

    public SavedEventViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindEvent(Event event) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.nameTextView);
        TextView timeTextView = (TextView) mView.findViewById(R.id.timeTextView);
        TextView group_nameTextView = (TextView) mView.findViewById(R.id.group_nameTextView);
        mSoberPDXIcon = (ImageView) mView.findViewById(R.id.soberPDXIcon);

        nameTextView.setText(event.getName());
        timeTextView.setText("Date and Time: " + event.getDateTimeGroup());
        group_nameTextView.setText("MeetUp Group Name: " + event.getGroup_name());
    }

    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.8f)
                .scaleX(0.7f)
                .scaleY(0.7f)
                .rotation(180f)
                .setDuration(500);

    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(0.5f)
                .scaleY(0.5f)
                .rotation(540f)
                .setDuration(750);
    }

}
