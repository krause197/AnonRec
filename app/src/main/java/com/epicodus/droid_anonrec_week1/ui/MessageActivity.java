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

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.backButton)
    Button mbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        mbackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MessageActivity.this, "Accept the Things You Cannot Change", Toast.LENGTH_LONG).show();
        if (v == mbackButton) {
            Intent intent = new Intent(MessageActivity.this, HomePageActivity.class);
            startActivity(intent);
        }
    }
}
