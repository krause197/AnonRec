package com.epicodus.anonrec.adapters.meetups;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.anonrec.models.Event;
import com.epicodus.anonrec.ui.meetups.MeetupDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 12/13/16.
 */
public class EventPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Event> mEvents;
    private String mSource;

    public EventPagerAdapter(FragmentManager fm, ArrayList<Event> events, String source) {
        super(fm);
        mEvents = events;
        mSource = source;
    }

    @Override
    public Fragment getItem(int position) {
        return MeetupDetailFragment.newInstance(mEvents, position, mSource);
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
