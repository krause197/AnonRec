package com.epicodus.droid_anonrec_week1.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/12/16.
 */
@Parcel
public class Profile {
    String name;
    String userIcon;

    public Profile () {}

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
