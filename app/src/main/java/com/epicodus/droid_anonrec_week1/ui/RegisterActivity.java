package com.epicodus.droid_anonrec_week1.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.droid_anonrec_week1.R;
import com.epicodus.droid_anonrec_week1.models.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.userNameEditText) EditText mUserNameEditText;
    @Bind(R.id.photoUrlEditText) EditText mPhotoUrlEditText;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @Bind(R.id.loginTextView) TextView mLoginTextView;
    @Bind(R.id.submitButton) Button mSubmitButton;

    public static final String TAG = RegisterActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String mName;
    private String mPhotoUrl;
    private ProgressDialog mAuthProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        createAuthProgressDialog();

        mLoginTextView.setOnClickListener(this);
        mSubmitButton.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View view) {

        if (view == mSubmitButton) {
            createNewUser();
            createNewProfile();
        }

        if (view == mLoginTextView ) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Creating User Account Profile");
        mAuthProgressDialog.setMessage("I searched for the enemy that I could not see, when I looked in the mirror the enemy was me ...");
        mAuthProgressDialog.setCancelable(false);
    }

    private boolean validPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPasswordEditText.setError("Password must be at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mPasswordEditText.setError("Password does not match");
            return false;
        }
        return true;
    }

    private boolean validEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmailEditText.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean validName(String name) {
        if (name.equals("")) {
            mUserNameEditText.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private void createNewUser() {
        final String email = mEmailEditText.getText().toString().trim();
        mName = mUserNameEditText.getText().toString().trim();
        mPhotoUrl = mPhotoUrlEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

        boolean validPassword = validPassword(password, confirmPassword);
        boolean validEmail = validEmail(email);
        boolean validName = validName(mName);

        if (!validEmail || !validName || !validPassword) return;

        mAuthProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        mAuthProgressDialog.dismiss();

                        if (task.isSuccessful()) {
                            createFirebaseUserProfile(task.getResult().getUser());

                        } else {
                            Toast.makeText(RegisterActivity.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    public void createFirebaseUserProfile(final FirebaseUser user) {
        UserProfileChangeRequest addProfileData = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName)
                .setPhotoUri(Uri.parse(mPhotoUrl))
                .build();
        user.updateProfile(addProfileData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, user.getDisplayName());
                        } else {
                            Toast.makeText(RegisterActivity.this, "User Profile Created!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    private void createNewProfile() {
        final String email = mEmailEditText.getText().toString().trim();
        final String name = mUserNameEditText.getText().toString().trim();
        final String userIcon = mPhotoUrlEditText.getText().toString().trim();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        Profile profile = new Profile (name, email, userIcon, uid);
        if (!userIcon.equals("")) {
            profile.setUserIcon(userIcon);
        }
        DatabaseReference memberRef = FirebaseDatabase.getInstance().getReference("profiles").child(uid);
        memberRef.setValue(profile);
    }
}
