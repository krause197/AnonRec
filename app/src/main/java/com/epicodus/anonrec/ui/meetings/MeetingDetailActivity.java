package com.epicodus.anonrec.ui.meetings;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.adapters.meetings.MeetingPagerAdapter;
import com.epicodus.anonrec.models.Meeting;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MeetingDetailActivity extends AppCompatActivity {
    @Bind(R.id.meetingViewPager) ViewPager mMeetingViewPager;
    private MeetingPagerAdapter adapterViewPager;
    ArrayList<Meeting> mMeetings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_detail);
        ButterKnife.bind(this);

        mMeetings = Parcels.unwrap(getIntent().getParcelableExtra("meetings"));

        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new MeetingPagerAdapter(getSupportFragmentManager(), mMeetings);
        mMeetingViewPager.setAdapter(adapterViewPager);
        mMeetingViewPager.setCurrentItem(startingPosition);
    }
}
