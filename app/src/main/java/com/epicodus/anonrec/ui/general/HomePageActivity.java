package com.epicodus.anonrec.ui.general;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.epicodus.anonrec.R;
import com.epicodus.anonrec.ui.meetups.SavedEventListActivity;
import com.epicodus.anonrec.ui.meetings.MeetingActivity;
import com.epicodus.anonrec.ui.meetups.MeetupActivity;
import com.epicodus.anonrec.ui.messages.MessageActivity;
import com.epicodus.anonrec.util.Android_Gesture_Detector;
import com.epicodus.anonrec.util.ToastMessage;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener, View.OnTouchListener, ToastMessage {

    @Bind(R.id.meetingButton) Button mMeetingButton;
    @Bind(R.id.meetupButton) Button mMeetupButton;
    @Bind(R.id.messageButton) Button mMessageButton;
    @Bind(R.id.savedMeetupButton) Button mSavedMeetupButton;

    private GestureDetector mGestureDetector;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 500;
    private ImageView mLogo;






    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mLogo = (ImageView) findViewById(R.id.defaultUserIcon);
        context= this;
        ButterKnife.bind(this);

        mMeetingButton.setOnClickListener(this);
        mMeetupButton.setOnClickListener(this);
        mMessageButton.setOnClickListener(this);
        mSavedMeetupButton.setOnClickListener(this);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);



        Android_Gesture_Detector android_gesture_detector = new Android_Gesture_Detector(){
            View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup)findViewById(R.id.toastLayout));
            @Override
            public void onSwipeUp() {
                Toast toast = new Toast(context);
                ImageView upImage = (ImageView)toastView.findViewById(R.id.toastImage);
                upImage.setImageResource(R.drawable.upimage);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(toastView);

                toast.show();

            }

            @Override
            public void onSwipeDown() {
                Toast toast = new Toast(context);
                ImageView downImage = (ImageView)toastView.findViewById(R.id.toastImage);
                downImage.setImageResource(R.drawable.downimage);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(toastView);

                toast.show();
            }

            @Override
            public void onSwipeLeft() {
                Toast toast = new Toast(context);
                ImageView leftImage = (ImageView)toastView.findViewById(R.id.toastImage);
                leftImage.setImageResource(R.drawable.leftimage);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(toastView);

                toast.show();
            }

            @Override
            public void onSwipeRight() {
                Toast toast = new Toast(context);
                ImageView rightImage = (ImageView)toastView.findViewById(R.id.toastImage);
                rightImage.setImageResource(R.drawable.rightimage);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(toastView);

                toast.show();

            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {

                return true;
            }
        };

        mGestureDetector = new GestureDetector(this, android_gesture_detector);
        mLogo.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view == mLogo) {
            mGestureDetector.onTouchEvent(motionEvent);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;


        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
                if (speed > SHAKE_THRESHOLD) {
                    getToast();
                    last_x = x;
                    last_y = y;
                    last_z = z;
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void getToast(){
        Random random = new Random();
        int randomMsgIndex = random.nextInt(toastMessages.length -1);
        Toast.makeText(HomePageActivity.this, toastMessages[randomMsgIndex], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (v == mMeetingButton) {
            getToast();
            Intent intent = new Intent(HomePageActivity.this, MeetingActivity.class);
            startActivity(intent);
        }
        if (v == mMeetupButton) {
            getToast();
            Intent intent = new Intent(HomePageActivity.this, MeetupActivity.class);
            startActivity(intent);
        }
        if (v == mMessageButton) {
            getToast();
            Intent intent = new Intent(HomePageActivity.this, MessageActivity.class);
            startActivity(intent);
        }
        if (v == mSavedMeetupButton) {
            getToast();
            Intent intent = new Intent(HomePageActivity.this, SavedEventListActivity.class);
            startActivity(intent);
        }
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
        if (id == R.id.action_meeting) {
            getToast();
            Intent intent = new Intent(HomePageActivity.this, MeetingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_profile) {
            getToast();
            Intent intent = new Intent(HomePageActivity.this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_meetup) {
            getToast();
            Intent intent = new Intent(HomePageActivity.this, MeetupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_message) {
            getToast();
            Intent intent = new Intent(HomePageActivity.this, MessageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_saved) {
            getToast();
            Intent intent = new Intent(HomePageActivity.this, SavedEventListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
