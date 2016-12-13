package com.epicodus.droid_anonrec_week1.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.droid_anonrec_week1.models.Event;
import com.epicodus.droid_anonrec_week1.ui.MeetupDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 12/13/16.
 */
public class EventPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Event> mEvents;

    public EventPagerAdapter(FragmentManager fm, ArrayList<Event> events) {
        super(fm);
        mEvents = events;
    }

    @Override
    public Fragment getItem(int position) {
        return MeetupDetailFragment.newInstance(mEvents.get(position));
    }

    @Override
    public int getCount() {
        return mEvents.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mEvents.get(position).getName();
    }
}
