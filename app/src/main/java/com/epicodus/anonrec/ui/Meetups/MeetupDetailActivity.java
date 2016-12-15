package com.epicodus.anonrec.ui.meetups;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.adapters.EventPagerAdapter;
import com.epicodus.anonrec.models.Event;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MeetupDetailActivity extends AppCompatActivity {
    @Bind(R.id.meetupDetailViewPager) ViewPager mMeetupDetailViewPager;
    private EventPagerAdapter adapterViewPager;
    ArrayList<Event> mEvents = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetup_detail);
        ButterKnife.bind(this);

        mEvents = Parcels.unwrap(getIntent().getParcelableExtra("events"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new EventPagerAdapter(getSupportFragmentManager(), mEvents);
        mMeetupDetailViewPager.setAdapter(adapterViewPager);
        mMeetupDetailViewPager.setCurrentItem(startingPosition);
    }
}
