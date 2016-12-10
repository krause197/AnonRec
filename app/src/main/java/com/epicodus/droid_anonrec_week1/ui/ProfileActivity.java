package com.epicodus.droid_anonrec_week1.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.droid_anonrec_week1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.homeButton) Button mHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        mHomeButton.setOnClickListener(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String homegroup = intent.getStringExtra("homegroup");
        String neighborhood = intent.getStringExtra("neighborhood");
        mName.setText(name);
        mHomegroup.setText(homegroup);
        mNeighborhood.setText(neighborhood);
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(ProfileActivity.this, "More Will Be Revealed", Toast.LENGTH_LONG).show();
        if (v == mHomeButton) {
            Intent intent = new Intent(ProfileActivity.this, HomePageActivity.class);
            intent.putExtra("name", mName.getText().toString());
            startActivity(intent);
        }
    }
}