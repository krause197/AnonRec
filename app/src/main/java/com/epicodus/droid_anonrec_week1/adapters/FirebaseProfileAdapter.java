//package com.epicodus.droid_anonrec_week1.adapters;
//
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentPagerAdapter;
//
//import com.epicodus.droid_anonrec_week1.models.Profile;
//import com.epicodus.droid_anonrec_week1.ui.ProfileDetailFragment;
//
//
//import java.util.ArrayList;
//
///**
// * Created by Guest on 12/12/16.
// */
//public class FirebaseProfileAdapter extends FragmentPagerAdapter {
//    private ArrayList<Profile> mProfile;
//
//
//    public FirebaseProfileAdapter(FragmentManager fm, ArrayList<Profile> profile) {
//        super(fm);
//        mProfile = profile;
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        return ProfileDetailFragment.newInstance(mProfile.get(position));
//    }
//
//    @Override
//    public int getCount() {
//        return 1;
//    }
//
////    @Override
////    public CharSequence getPageTitle(int position) {
////        return mProfile.get(position).getName();
////    }
//}


