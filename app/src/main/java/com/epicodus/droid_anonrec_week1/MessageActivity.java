package com.epicodus.droid_anonrec_week1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.backButton)
    Button mbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
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