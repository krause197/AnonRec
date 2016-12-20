package com.epicodus.anonrec.ui.meetups;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.adapters.meetups.EventListAdapter;
import com.epicodus.anonrec.models.Event;
import com.epicodus.anonrec.services.MeetupService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MeetupListFragment extends Fragment {
    public static final String TAG = MeetupActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private EventListAdapter mEventAdapter;

    public ArrayList<Event> mEvents = new ArrayList<>();


    public MeetupListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meetup_list, container, false);
        ButterKnife.bind(this, view);

        String groupName = "pdx-sober";

        getEvents(groupName);
        return view;
    }

    private void getEvents(String groupName) {
        final MeetupService meetupService = new MeetupService();
        meetupService.findEvents(groupName, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mEvents = meetupService.processResults(response);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mEventAdapter = new EventListAdapter(getActivity(), mEvents);
                        mRecyclerView.setAdapter(mEventAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }


}
