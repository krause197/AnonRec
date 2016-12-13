//package com.epicodus.droid_anonrec_week1.ui;
//
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.epicodus.droid_anonrec_week1.R;
//import com.epicodus.droid_anonrec_week1.adapters.FirebaseProfileAdapter;
//import com.epicodus.droid_anonrec_week1.models.Profile;
//import com.google.firebase.auth.FirebaseAuth;
//import com.squareup.picasso.Picasso;
//
//import org.parceler.Parcels;
//
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
//public class ProfileDetailFragment extends Fragment {
//    private static final int MAX_WIDTH = 400;
//    private static final int MAX_HEIGHT = 300;
//
//
//    @Bind(R.id.name) TextView mName;
//    @Bind(R.id.userIcon) ImageView mUserIcon;
//    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
//
//    private Profile mProfile;
//
//    public static ProfileDetailFragment newInstance(Profile profile) {
//        ProfileDetailFragment profileDetailFragment = new ProfileDetailFragment();
//        Bundle args = new Bundle();
//        args.putParcelable("profile", Parcels.wrap(profile));
//        profileDetailFragment.setArguments(args);
//        return profileDetailFragment;
//
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mProfile = Parcels.unwrap(getArguments().getParcelable("profile"));
//    }
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
//        View view = inflater.inflate(R.layout.activity_profile_detail_fragment, container, false);
//        ButterKnife.bind(this, view);
//
//        Picasso.with(view.getContext())
//                .load(mProfile.getUserIcon())
//                .resize(MAX_WIDTH, MAX_HEIGHT)
//                .centerCrop()
//                .into(mUserIcon);
//
//
//        mName.setText(mProfile.getName());
//
//        return view;
//    }
//}
