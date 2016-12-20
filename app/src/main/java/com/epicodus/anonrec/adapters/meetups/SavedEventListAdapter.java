package com.epicodus.anonrec.adapters.meetups;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.constants.MeetupConstants;
import com.epicodus.anonrec.models.Event;
import com.epicodus.anonrec.ui.meetups.MeetupDetailActivity;
import com.epicodus.anonrec.ui.meetups.MeetupDetailFragment;
import com.epicodus.anonrec.util.ItemTouchHelperAdapter;
import com.epicodus.anonrec.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Guest on 12/16/16.
 */
public class SavedEventListAdapter extends FirebaseRecyclerAdapter<Event, SavedEventViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Event> mEvents = new ArrayList<>();
    private int mOrientation;


    public SavedEventListAdapter(Class<Event> modelClass, int modelLayout, Class<SavedEventViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mEvents.add(dataSnapshot.getValue(Event.class));
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
    protected void populateViewHolder(final SavedEventViewHolder viewHolder, Event model, int position) {
        viewHolder.bindEvent(model);

        mOrientation = viewHolder.itemView.getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            createDetailFragment(0);
        }
        viewHolder.mSoberPDXIcon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = viewHolder.getAdapterPosition();
                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    createDetailFragment(itemPosition);
                } else {
                    Intent intent = new Intent(mContext, MeetupDetailActivity.class);
                    intent.putExtra(MeetupConstants.EXTRA_KEY_POSITION, itemPosition);
                    intent.putExtra(MeetupConstants.EXTRA_KEY_EVENTS, Parcels.wrap(mEvents));
                    intent.putExtra(MeetupConstants.KEY_SOURCE, MeetupConstants.SOURCE_SAVED);
                    mContext.startActivity(intent);
                }

            }
        });
    }

    private void createDetailFragment(int position) {
        MeetupDetailFragment detailFragment = MeetupDetailFragment.newInstance(mEvents, position, MeetupConstants.SOURCE_SAVED);
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.meetupDetailContainer, detailFragment);
        ft.commit();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mEvents, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    private void setIndexInFirebase() {
        for (Event event : mEvents) {
            int index = mEvents.indexOf(event);
            DatabaseReference ref = getRef(index);
            event.setIndex(Integer.toString(index));
            ref.setValue(event);
        }
    }

    @Override
    public void onItemDismiss(int position) {
        mEvents.remove(position);
        getRef(position).removeValue();

    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }

}
