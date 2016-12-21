package com.epicodus.anonrec.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/13/16.
 */
@Parcel
public class Meeting {
    String day;
    String time;
    String groupname;
    String location;
    String street;
    String city;
    String state;
    private String fullAddress;
    private String pushId;

    public Meeting() {}

    public Meeting(String day, String time, String groupname, String location, String street, String city, String state) {
        this.day = day;
        this.time = time;
        this.groupname = groupname;
        this.location = location;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getGroupname() {
        return groupname;
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
        fullAddress = street + ", " + city + ", " + state;
        return fullAddress;
    }

    public String getPushId() {return pushId;}
    public void setPushId(String pushId) {this.pushId = pushId;}
}

