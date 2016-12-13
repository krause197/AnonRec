package com.epicodus.droid_anonrec_week1.ui;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.droid_anonrec_week1.R;
import com.epicodus.droid_anonrec_week1.models.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.net.URI;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileDetailFragment extends Fragment {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Bind(R.id.name) TextView mName;
    @Bind(R.id.userIcon) ImageView mUserIcon;

    private Profile mProfile;

    public static ProfileDetailFragment newInstance(Profile profile) {
        ProfileDetailFragment profileDetailFragment = new ProfileDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("profile", Parcels.wrap(profile));
        profileDetailFragment.setArguments(args);
        return profileDetailFragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProfile = Parcels.unwrap(getArguments().getParcelable("profile"));

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    for (UserInfo profile : user.getProviderData()) {
                        String name = profile.getDisplayName();
                        Uri photoUrl = profile.getPhotoUrl();
                    }
                } else {
                }


                @Override
                public void onStart() {
                    super.onStart();
                    mAuth.addAuthStateListener(mAuthListener);
                }

                @Override
                public void onStop() {
                    super.onStop();
                    if (mAuthListener != null) {
                        mAuth.removeAuthStateListener(mAuthListener);
                    }
                }

            }
        };
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
        View view = inflater.inflate(R.layout.activity_profile_detail_fragment, container, false);
        ButterKnife.bind(this, view);

        String name = mAuthListener.onAuthStateChanged().name;
        URI photoUrl = mAuthListener.onAuthStateChanged().photoUrl;

        Picasso.with(view.getContext())
                .load(mProfile.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mUserIcon);


        mName.setText(name);
        mUserIcon.setImageURI(photoUrl);

        return view;
    }
}
