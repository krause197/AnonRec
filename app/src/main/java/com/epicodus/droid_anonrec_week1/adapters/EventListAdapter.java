package com.epicodus.droid_anonrec_week1.adapters;

import android.view.ViewGroup;

import com.epicodus.droid_anonrec_week1.R;
import com.epicodus.droid_anonrec_week1.models.Event;

import butterknife.ButterKnife;

/**
 * Created by Guest on 12/2/16.
 */
public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder>  {
    private ArrayList<Event> mEvents = new ArrayList<>();

    private Context mContext;

    public EventListAdapter(Context context, Arraylist<Event> Events) {
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
    public void onBindViewHolder(EventListAdapter.EventViewHolder holder, int position) {
        holder.bindEvent(mEvents.get(position));
    }

    @Override
    public int getItemCount(){
        return mEvents.size();
    }

    public class EventViewHolder extents RecyclerView.ViewHolder {
        @Bind(R.id.placeholderTextView) TextView mPlaceholderTextView;

        private Context mContext;

        public EventViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindEvent(Event event){
            mPlaceholderTextView.setText(event.getPlaceholder());
        }
    }
}
