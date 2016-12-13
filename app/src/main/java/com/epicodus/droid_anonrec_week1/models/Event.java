package com.epicodus.droid_anonrec_week1.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/4/16.
 */
@Parcel
public class Event {
    String name;
    String dateTimeGroup;
    String event_Url;
    String fullAddress;
    String yes_rsvp_count;
    String maybe_rsvp_count;
    String group_name;
    String who;
    private String pushId;

    public Event() {}

    public Event (String name, String dateTimeGroup, String event_url, String fullAddress, String yes_rsvp_count, String maybe_rsvp_count, String group_name, String who) {
        this.name = name;
        this.dateTimeGroup = dateTimeGroup;
        this.event_Url = event_url;
        this.fullAddress = fullAddress;
        this.yes_rsvp_count = yes_rsvp_count;
        this.maybe_rsvp_count = maybe_rsvp_count;
        this.group_name = group_name;
        this.who = who;
    }

    public String getName() { return name;}
    public String getDateTimeGroup() { return dateTimeGroup;}
    public String getEvent_Url() { return event_Url;}
    public String getAddress() { return fullAddress;}
    public String getYes_rsvp_count() { return yes_rsvp_count;}
    public String getMaybe_rsvp_count() { return maybe_rsvp_count;}
    public String getGroup_name() { return group_name;}
    public String getWho() { return who;}
    public String getPushId() {return pushId;}
    public void setPushId(String pushId) {this.pushId = pushId;}
}
