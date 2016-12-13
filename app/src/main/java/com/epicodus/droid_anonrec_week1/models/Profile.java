package com.epicodus.droid_anonrec_week1.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/12/16.
 */
@Parcel
public class Profile {
    String name = getUser();
    String userIcon = getUserImage();

    public Profile() {}

    public String getUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String name = "";
        if (user != null) {
            name = user.getDisplayName();
        }
        return name;
    }

    public String getUserImage() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userIcon = "";
        if (user != null) {
            userIcon = user.getPhotoUrl().toString();
        }
        return userIcon;
    }

    public Profile(String name, String userIcon) {
        this.name = name;
        this.userIcon = userIcon;
    }

    public String getName() {

        return name;
    }

    public String getUserIcon() {
        return userIcon;
    }
}
