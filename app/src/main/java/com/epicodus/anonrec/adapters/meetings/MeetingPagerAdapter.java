package com.epicodus.anonrec.adapters.meetings;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.anonrec.models.Meeting;
import com.epicodus.anonrec.ui.meetings.MeetingDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 12/14/16.
 */
public class MeetingPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Meeting> mMeetings;

    public MeetingPagerAdapter(FragmentManager fm, ArrayList<Meeting> meetings) {
        super(fm);
        mMeetings = meetings;
    }

    @Override
    public Fragment getItem(int position) {
        return MeetingDetailFragment.newInstance(mMeetings.get(position));
    }

    @Override
    public int getCount() {
        return mMeetings.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMeetings.get(position).getGroupname();
    }
}
