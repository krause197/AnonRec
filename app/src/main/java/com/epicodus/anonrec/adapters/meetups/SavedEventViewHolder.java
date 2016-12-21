package com.epicodus.anonrec.adapters.meetups;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.models.Event;
import com.epicodus.anonrec.util.ItemTouchHelperViewHolder;

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
                .rotation(360f)
                .setDuration(500);

    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .rotation(360f)
                .setDuration(750);
    }

}
