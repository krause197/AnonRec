package com.epicodus.droid_anonrec_week1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.epicodus.droid_anonrec_week1.R;
import com.epicodus.droid_anonrec_week1.adapters.FirebaseProfileAdapter;
import com.epicodus.droid_anonrec_week1.models.Profile;
import com.google.firebase.auth.FirebaseAuth;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.homeButton) Button mHomeButton;
    private FirebaseProfileAdapter adapterViewPager;
    ArrayList<Profile> mProfile = new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        mProfile = Parcels.unwrap(getIntent().getParcelableExtra("profile"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new FirebaseProfileAdapter(getSupportFragmentManager(), mProfile);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

        mHomeButton.setOnClickListener(this);

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