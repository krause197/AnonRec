package com.epicodus.anonrec.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.models.Meeting;


import java.util.ArrayList;

/**
 * Created by Guest on 12/14/16.
 */
public class MeetingListAdapter extends RecyclerView.Adapter<MeetingViewHolder>  {
    private ArrayList<Meeting> mMeetings = new ArrayList<>();

    private Context mContext;

    public MeetingListAdapter(Context context, ArrayList<Meeting> meetings) {
        mContext = context;
        mMeetings = meetings;
    }

    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_list_item, parent, false);
        MeetingViewHolder viewHolder = new MeetingViewHolder(view);
        return viewHolder;
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
