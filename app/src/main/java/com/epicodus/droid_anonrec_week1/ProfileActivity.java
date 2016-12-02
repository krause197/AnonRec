package com.epicodus.droid_anonrec_week1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.homeButton)
    Button mHomeButton;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.homegroup)
    TextView mHomegroup;
    @Bind(R.id.neighborhood)
    TextView mNeighborhood;


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