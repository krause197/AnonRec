package com.epicodus.anonrec.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/13/16.
 */
@Parcel
public class Meeting {
    String day;
    String group_name;
    String location;
    String street;
    String city;
    String state;

    public Meeting() {}

    public Meeting(String day, String group_name, String location, String street, String city, String state) {
        this.day = day;
        this.group_name = group_name;
        this.location = location;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public String getDay() {
        return day;
    }

    public String getGroup_name() {
        return group_name;
    }

    public String getLocation() {
        return location;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
