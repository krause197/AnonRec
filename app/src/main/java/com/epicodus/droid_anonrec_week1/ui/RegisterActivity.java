package com.epicodus.droid_anonrec_week1.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.droid_anonrec_week1.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.homeGroupEditText2) EditText mHomeGroupEditText2;
    @Bind(R.id.neighborhoodEditText) EditText mNeighborhoodEditText;
    @Bind(R.id.submitButton) Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSubmitButton) {
            Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
            intent.putExtra("name", mNameEditText.getText().toString());
            intent.putExtra("homegroup", mHomeGroupEditText2.getText().toString());
            intent.putExtra("neighborhood", mNeighborhoodEditText.getText().toString());
            startActivity(intent);
        }
    }
}
