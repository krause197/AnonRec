package com.epicodus.droid_anonrec_week1.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.droid_anonrec_week1.services.MeetupService;
import com.epicodus.droid_anonrec_week1.R;
import com.epicodus.droid_anonrec_week1.adapters.EventListAdapter;
import com.epicodus.droid_anonrec_week1.models.Event;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class MeetupActivity extends AppCompatActivity {
    public static final String TAG = MeetupActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private EventListAdapter mAdapter;

    public ArrayList<Event> mEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetup);
        ButterKnife.bind(this);

        String groupName = "pdx-sober";

        getEvents(groupName);
    }

    private void getEvents(String groupName) {
        final MeetupService meetupService = new MeetupService();
        meetupService.getEvents(groupName, new CallBack() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mEvents = meetupService.processResults(response);

                MeetupService.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mEventListAdapter = new EventListAdapter(getApplicationContext(), mEvents);
                        mRecyclerView.setAdapter(mEventListAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MeetupActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
