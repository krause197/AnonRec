package com.epicodus.droid_anonrec_week1.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.epicodus.droid_anonrec_week1.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.welcomeButton) Button mWelcomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mWelcomeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "One Day At A Time", Toast.LENGTH_LONG).show();
        if (v == mWelcomeButton) {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);

        }
    }
}
