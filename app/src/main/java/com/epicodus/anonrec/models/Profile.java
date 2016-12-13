package com.epicodus.anonrec.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/12/16.
 */
@Parcel
public class Profile {
    String name;
    String userIcon;
    String pushId;
    String email;

    public Profile() {}


    public Profile(String name, String email, String userIcon, String uid) {
        this.name = name;
        this.email = email;
        this.userIcon = userIcon;
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
