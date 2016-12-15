package com.epicodus.anonrec.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.epicodus.anonrec.MeetingConstants;
import com.epicodus.anonrec.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MeetingActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.dayParameter) Spinner mDayParameter;
    @Bind(R.id.regionParameter) Spinner mRegionParameter;
    @Bind(R.id.searchButton) Button mSearchButton;

    Spinner dayParameter;
    Spinner regionParameter;
    Button searchButton;
    String selectDay;
    String selectRegion;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        ButterKnife.bind(this);
        mSearchButton.setOnClickListener(this);

        dayParameter = (Spinner) findViewById(R.id.dayParameter);
        regionParameter = (Spinner) findViewById(R.id.regionParameter);
        searchButton = (Button) findViewById(R.id.searchButton);

        dayParameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int position, long id) {
               selectDay = adapter.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView arg0) {

            }
        });

        regionParameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int position, long id) {
               selectRegion = adapter.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView arg0) {

            }
        });

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
    }


    @Override
    public void onClick(View v) {

        if (v == mSearchButton) {
            String day = selectDay.replaceAll("[^A-Za-z]+", "").toLowerCase();
            addDayToSharedPreferences(day);
            String region = selectRegion.replaceAll("[^A-Za-z]+", "").toLowerCase();
            addRegionToSharedPreferences(region);
            Intent intent = new Intent(MeetingActivity.this, MeetingListActivity.class);
            startActivity(intent);
        }

    }

    private void addDayToSharedPreferences(String day) {
        mEditor.putString(MeetingConstants.FIREBASE_QUERY_DAY, day).apply();
    }

    public void addRegionToSharedPreferences(String region) {
        mEditor.putString(MeetingConstants.FIREBASE_QUERY_REGION, region).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        if (id == R.id.action_home) {
            Intent intent = new Intent(MeetingActivity.this, HomePageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_profile) {
            Intent intent = new Intent(MeetingActivity.this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_meetup) {
            Intent intent = new Intent(MeetingActivity.this, MeetupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_message) {
            Intent intent = new Intent(MeetingActivity.this, MessageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_saved) {
            Intent intent = new Intent(MeetingActivity.this, SavedEventListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MeetingActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
