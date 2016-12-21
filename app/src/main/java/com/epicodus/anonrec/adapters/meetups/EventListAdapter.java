package com.epicodus.anonrec.adapters.meetups;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.constants.MeetupConstants;
import com.epicodus.anonrec.models.Event;
import com.epicodus.anonrec.ui.meetups.MeetupDetailActivity;
import com.epicodus.anonrec.ui.meetups.MeetupDetailFragment;
import com.epicodus.anonrec.util.OnEventSelectedListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Guest on 12/2/16.
 */
public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder>  {
    private ArrayList<Event> mEvents = new ArrayList<>();

    private Context mContext;
    private OnEventSelectedListener mOnEventSelectedListener;

    public EventListAdapter(Context context, ArrayList<Event> events, OnEventSelectedListener restaurantSelectedListener) {
        mContext = context;
        mEvents = events;
        mOnEventSelectedListener = restaurantSelectedListener;
    }

    @Override
    public EventListAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        EventViewHolder viewHolder = new EventViewHolder(view, mEvents, mOnEventSelectedListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventListAdapter.EventViewHolder holder, int position){
        holder.bindEvent(mEvents.get(position));
    }

    @Override
    public int getItemCount(){
        return mEvents.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.nameTextView) TextView mNameTextView;
        @Bind(R.id.timeTextView) TextView mTimeTextView;
        @Bind(R.id.group_nameTextView) TextView mGroup_nameTextView;

        private int mOrientation;
        private Context mContext;
        private ArrayList<Event> mEvents = new ArrayList<>();
        private OnEventSelectedListener mEventSelectedListener;

        public EventViewHolder(View itemView, ArrayList<Event> events, OnEventSelectedListener eventSelectedListener){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
            mOrientation = itemView.getResources().getConfiguration().orientation;
            mEvents = events;
            mEventSelectedListener = eventSelectedListener;
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
            itemView.setOnClickListener(this);
        }

        private void createDetailFragment(int position) {
            MeetupDetailFragment detailFragment = MeetupDetailFragment.newInstance(mEvents, position, MeetupConstants.SOURCE_FIND);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.meetupDetailContainer, detailFragment);
            ft.commit();
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            mEventSelectedListener.OnEventSelected(itemPosition, mEvents, MeetupConstants.SOURCE_FIND);
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, MeetupDetailActivity.class);
                intent.putExtra(MeetupConstants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(MeetupConstants.EXTRA_KEY_EVENTS, Parcels.wrap(mEvents));
                intent.putExtra(MeetupConstants.KEY_SOURCE, MeetupConstants.SOURCE_FIND);
                mContext.startActivity(intent);
            }

        }

        public void bindEvent(Event event) {
            mNameTextView.setText(event.getName());
            mTimeTextView.setText("Date and Time: " + event.getDateTimeGroup());
            mGroup_nameTextView.setText("MeetUp Group Name: " + event.getGroup_name());
        }
    }
}
