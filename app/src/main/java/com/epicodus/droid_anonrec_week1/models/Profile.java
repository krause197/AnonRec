package com.epicodus.droid_anonrec_week1.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/12/16.
 */
@Parcel
public class Profile {
    String name;
    String userIcon = "@drawable/icon";
    String pushId;
    String email;

    public Profile() {}


    public Profile(String name, String userIcon, String email, String uid) {
        this.name = name;
        this.userIcon = userIcon;
        this.email = email;
        this.pushId = uid;
    }

    public String getName() {
        return name;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public String getEmail() {
        return email;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getPushId() {
        return pushId;
    }

}
