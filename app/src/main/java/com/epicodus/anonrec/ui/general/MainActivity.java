package com.epicodus.anonrec.ui.general;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.util.ToastMessage;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, ToastMessage{
    @Bind(R.id.welcomeButton) Button mWelcomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mWelcomeButton.setOnClickListener(this);
    }

    @Override
    public void getToast(){
        Toast.makeText(MainActivity.this, toastMessages[randomMsgIndex], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        getToast();
        if (v == mWelcomeButton) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();

        }
    }
}
