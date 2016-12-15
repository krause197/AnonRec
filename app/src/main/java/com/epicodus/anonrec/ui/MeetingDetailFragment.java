package com.epicodus.anonrec.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.anonrec.MeetingConstants;
import com.epicodus.anonrec.R;
import com.epicodus.anonrec.models.Meeting;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;


import butterknife.Bind;
import butterknife.ButterKnife;

public class MeetingDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.group_nameTextView) TextView mGroup_NameLabel;
    @Bind(R.id.dayTextView) TextView mDayLabel;
    @Bind(R.id.timeTextView) TextView mTimeLabel;
    @Bind(R.id.locationTextView) TextView mLocationLabel;
    @Bind(R.id.fullAddressTextView) TextView mFullAddressLabel;
    @Bind(R.id.saveMeetingButton) TextView mSaveMeetingButton;


    private Meeting mMeeting;

    public static MeetingDetailFragment newInstance(Meeting meeting) {
        MeetingDetailFragment meetingDetailFragment = new MeetingDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("meeting", Parcels.wrap(meeting));
        meetingDetailFragment.setArguments(args);
        return meetingDetailFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeeting = Parcels.unwrap(getArguments().getParcelable("meeting"));
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting_detail, container, false);
        ButterKnife.bind(this, view);

        mGroup_NameLabel.setText(mMeeting.getGroup_name());
        mDayLabel.setText(mMeeting.getDay());
        mTimeLabel.setText(mMeeting.getTime());
        mLocationLabel.setText(mMeeting.getLocation());
        mFullAddressLabel.setText(mMeeting.getFullAddress());

        mSaveMeetingButton.setOnClickListener(this);
        mFullAddressLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mFullAddressLabel) {
            String addressNoSpace = mMeeting.getFullAddress().replaceAll("\\s+", "");
            Intent mapIntent = new Intent (Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0q=" + addressNoSpace));
            startActivity(mapIntent);
        }
        if (v == mSaveMeetingButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference meetingRef = FirebaseDatabase.getInstance().getReference(MeetingConstants.FIREBASE_CHILD_MEETINGS).child(uid);

            DatabaseReference pushRef = meetingRef.push();
            String pushId = pushRef.getKey();
            mMeeting.setPushId(pushId);
            pushRef.setValue(mMeeting);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
