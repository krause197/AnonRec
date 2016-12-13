package com.epicodus.droid_anonrec_week1.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.droid_anonrec_week1.R;

import com.epicodus.droid_anonrec_week1.models.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.homeButton) Button mHomeButton;
    @Bind(R.id.name) TextView mName;
    @Bind(R.id.userIcon) ImageView mUserIcon;
    public Profile mProfile;
//    private FirebaseProfileAdapter adapterViewPager;
//    ArrayList<Profile> mProfile = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mName.setText(getUser());
        ImageView imgView = (ImageView) findViewById(R.id.userIcon);
        imgView.setImageURI(Uri.parse(getUserImage()));
        ButterKnife.bind(this);


//
//        mProfile = Parcels.unwrap(getIntent().getParcelableExtra("profile"));
//        int startingPosition = getIntent().getIntExtra("position", 0);
//
//        adapterViewPager = new FirebaseProfileAdapter(getSupportFragmentManager(), mProfile);
//        mViewPager.setAdapter(adapterViewPager);
//        mViewPager.setCurrentItem(startingPosition);

        mHomeButton.setOnClickListener(this);

    }
    public String getUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String name = "";
        if (user != null) {
            name = user.getDisplayName();
        }
        return name;
    }

    public String getUserImage() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userIcon = "";
        if (user != null) {
            userIcon = user.getPhotoUrl().toString();
        }
        return userIcon;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(ProfileActivity.this, "More Will Be Revealed", Toast.LENGTH_LONG).show();
        if (v == mHomeButton) {
            Intent intent = new Intent(ProfileActivity.this, HomePageActivity.class);
            startActivity(intent);
        }
    }
}