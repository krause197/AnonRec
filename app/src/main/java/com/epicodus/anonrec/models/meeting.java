package com.epicodus.anonrec.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/13/16.
 */
@Parcel
public class Meeting {
    String day;
    String time;
    String group_name;
    String location;
    String street;
    String city;
    String state;
    private String pushId;

    public Meeting() {}

    public Meeting(String day, String time, String group_name, String location, String street, String city, String state) {
        this.day = day;
        this.time = time;
        this.group_name = group_name;
        this.location = location;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    String fullAddress = street + ", " + city + ", " + state;

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
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

    public String getFullAddress() {
        return fullAddress;
    }

    public String getPushId() {return pushId;}
    public void setPushId(String pushId) {this.pushId = pushId;}
}


