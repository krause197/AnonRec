package com.epicodus.droid_anonrec_week1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.droid_anonrec_week1.R;
import com.epicodus.droid_anonrec_week1.models.Event;
import com.squareup.picasso.Picasso;

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

    public class EventViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.nameTextView) TextView mNameTextView;
        @Bind(R.id.descriptionTextView) TextView mDescriptionTextView;
        @Bind(R.id.timeTextView) TextView mTimeTextView;
        @Bind(R.id.event_urlTextView) TextView mEvent_urlTextView;
        @Bind(R.id.addressTextView) TextView mAddressTextView;
        @Bind(R.id.cityTextView) TextView mCityTextView;
        @Bind(R.id.stateTextView) TextView mStateTextView;
        @Bind(R.id.zipTextView) TextView mZipTextView;
        @Bind(R.id.yes_rsvp_countTextView) TextView mYes_rsvp_countTextView;
        @Bind(R.id.maybe_rsvp_countTextView) TextView mMaybe_rsvp_countTextView;
        @Bind(R.id.group_nameTextView) TextView mGroup_nameTextView;
        @Bind(R.id.whoTextView) TextView mWhoTextView;


        private Context mContext;

        public EventViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindEvent(Event event) {
            mNameTextView.setText(event.getName());
            mDescriptionTextView.setText(event.getDescription());
            mTimeTextView.setText(event.getTime());
            mEvent_urlTextView.setText(event.getEvent_Url());
            mAddressTextView.setText(event.getAddress());
            mCityTextView.setText(event.getCity());
            mStateTextView.setText(event.getState());
            mZipTextView.setText(event.getZip());
            mYes_rsvp_countTextView.setText(event.getYes_rsvp_count());
            mMaybe_rsvp_countTextView.setText(event.getMaybe_rsvp_count());
            mGroup_nameTextView.setText(event.getGroup_name());
            mWhoTextView.setText(event.getWho());
        }
    }
}
