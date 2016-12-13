package com.epicodus.droid_anonrec_week1.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/4/16.
 */
@Parcel
public class Event {
    private String mName;
    private String mDateTimeGroup;
    private String mEvent_Url;
    private String mFullAddress;
    private String mYes_rsvp_count;
    private String mMaybe_rsvp_count;
    private String mGroup_name;
    private String mWho;

    public Event() {}

    public Event (String name, String dateTimeGroup, String event_url, String fullAddress, String yes_rsvp_count, String maybe_rsvp_count, String group_name, String who) {
        this.mName = name;
        this.mDateTimeGroup = dateTimeGroup;
        this.mEvent_Url = event_url;
        this.mFullAddress = fullAddress;
        this.mYes_rsvp_count = yes_rsvp_count;
        this.mMaybe_rsvp_count = maybe_rsvp_count;
        this.mGroup_name = group_name;
        this.mWho = who;
    }

    public String getName() { return mName;}
    public String getDateTimeGroup() { return mDateTimeGroup;}
    public String getEvent_Url() { return mEvent_Url;}
    public String getAddress() { return mFullAddress;}
    public String getYes_rsvp_count() { return mYes_rsvp_count;}
    public String getMaybe_rsvp_count() { return mMaybe_rsvp_count;}
    public String getGroup_name() { return mGroup_name;}
    public String getWho() { return mWho;}

}
