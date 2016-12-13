package com.epicodus.droid_anonrec_week1.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.droid_anonrec_week1.MeetupConstants;
import com.epicodus.droid_anonrec_week1.R;
import com.epicodus.droid_anonrec_week1.models.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetupDetailFragment extends Fragment implements View.OnClickListener {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meetup_detail, container, false);
        ButterKnife.bind(this, view);

        mNameLabel.setText(mEvent.getName());
        mWhoLabel.setText(mEvent.getWho());
        mGroupNameLabel.setText(mEvent.getGroup_name());
        mWebsiteLabel.setText(mEvent.getEvent_Url());
        mTimeLabel.setText("Date and Time: " + mEvent.getDateTimeGroup());
        mYesLabel.setText("People going: " + mEvent.getYes_rsvp_count());
        mMaybeLabel.setText("People interested in going: " + mEvent.getMaybe_rsvp_count());
        mAddressLabel.setText(mEvent.getAddress());

        mWebsiteLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);
        mSaveEventButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mEvent.getEvent_Url()));
            startActivity(webIntent);
        }
        if (v == mAddressLabel) {
            String addressNoSpace = mEvent.getAddress().replaceAll("\\s+", "");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=" + addressNoSpace));
            startActivity(mapIntent);
        }
        if (v == mSaveEventButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference eventRef = FirebaseDatabase.getInstance().getReference(MeetupConstants.FIREBASE_CHILD_EVENTS).child(uid);
            DatabaseReference pushRef = eventRef.push();
            String pushId = pushRef.getKey();
            mEvent.setPushId(pushId);
            eventRef.setValue(mEvent);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

}
