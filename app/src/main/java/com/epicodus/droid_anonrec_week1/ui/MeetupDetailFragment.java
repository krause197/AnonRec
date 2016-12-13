package com.epicodus.droid_anonrec_week1.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.droid_anonrec_week1.R;
import com.epicodus.droid_anonrec_week1.models.Event;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetupDetailFragment extends Fragment {
    @Bind(R.id.nameTextView) TextView mNameLabel;
    @Bind(R.id.whoTextView) TextView mWhoLabel;
    @Bind(R.id.group_nameTextView) TextView mGroupNameLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.timeTextView) TextView mTimeLabel;
    @Bind(R.id.yes_rsvp_countTextView) TextView mYesLabel;
    @Bind(R.id.maybe_rsvp_countTextView) TextView mMaybeLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveEventButton) TextView mSaveEventButton;

    private Event mEvent;

    public static MeetupDetailFragment newInstance(Event event) {
        MeetupDetailFragment meetupDetailFragment = new MeetupDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("event", Parcels.wrap(event));
        meetupDetailFragment.setArguments(args);
        return meetupDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvent = Parcels.unwrap(getArguments().getParcelable("event"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meetup_detail, container, false);
        ButterKnife.bind(this, view);

        mNameLabel.setText(mEvent.getName());
        mWhoLabel.setText(mEvent.getWho());
        mGroupNameLabel.setText(mEvent.getGroup_name());
        mWebsiteLabel.setText(mEvent.getEvent_Url());
        mTimeLabel.setText(mEvent.getDateTimeGroup());
        mYesLabel.setText(mEvent.getYes_rsvp_count());
        mMaybeLabel.setText(mEvent.getMaybe_rsvp_count());
        mAddressLabel.setText(mEvent.getAddress());

        return view;
    }

}
