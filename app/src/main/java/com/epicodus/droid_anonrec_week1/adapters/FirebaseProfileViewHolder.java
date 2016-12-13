package com.epicodus.droid_anonrec_week1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//import com.epicodus.droid_anonrec_week1.R;
//import com.epicodus.droid_anonrec_week1.models.Profile;
//import com.squareup.picasso.Picasso;
//
///**
// * Created by Guest on 12/12/16.
// */
//public class FirebaseProfileViewHolder extends RecyclerView.ViewHolder {
//    private static final int MAX_WIDTH = 200;
//    private static final int MAX_HEIGHT = 200;
//
//    View mView;
//    Context mContext;
//
//    public FirebaseProfileViewHolder(View itemView) {
//        super(itemView);
//        mView = itemView;
//        mContext = itemView.getContext();
//    }
//
//    public void bindProfile(Profile profile) {
//        ImageView userIcon = (ImageView) mView.findViewById(R.id.userIcon);
//        TextView name = (TextView) mView.findViewById(R.id.name);
//
//        Picasso.with(mContext)
//                .load(profile.getUserIcon())
//                .resize(MAX_WIDTH, MAX_HEIGHT)
//                .centerCrop()
//                .into(userIcon);
//
//        name.setText(profile.getName());
//    }
//}
