package com.epicodus.anonrec.adapters.meetups;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.models.Event;
import com.epicodus.anonrec.ui.meetups.MeetupDetailActivity;

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

    public EventListAdapter(Context context, ArrayList<Event> events) {
        mContext = context;
        mEvents = events;
    }

    @Override
    public EventListAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        EventViewHolder viewHolder = new EventViewHolder(view);
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




        private Context mContext;

        public EventViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, MeetupDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("events", Parcels.wrap(mEvents));
            mContext.startActivity(intent);
        }

        public void bindEvent(Event event) {
            mNameTextView.setText(event.getName());
            mTimeTextView.setText("Date and Time: " + event.getDateTimeGroup());
            mGroup_nameTextView.setText("MeetUp Group Name: " + event.getGroup_name());
        }
    }
}
