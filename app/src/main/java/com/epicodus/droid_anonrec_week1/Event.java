package com.epicodus.droid_anonrec_week1;

/**
 * Created by Guest on 12/4/16.
 */
public class Event {
    private String mName;
    private String mDescription;
    private String mTime;
    private String mEvent_Url;
    private String mAddress;
    private String mState;
    private String mCity;
    private String mZip;
    private String mYes_rsvp_count;
    private String mMaybe_rsvp_count;
    private String mGroup_name;
    private String mWho;

    public Event (String name, String description, String time, String event_url, String address, String state, String city, String zip, String yes_rsvp_count, String maybe_rsvp_count, String group_name, String who) {
        this.mName = name;
        this.mDescription = description;
        this.mTime = time;
        this.mEvent_Url = event_url;
        this.mAddress = address;
        this.mState = state;
        this.mCity = city;
        this.mZip = zip;
        this.mYes_rsvp_count = yes_rsvp_count;
        this.mMaybe_rsvp_count = maybe_rsvp_count;
        this.mGroup_name = group_name;
        this.mWho = who;
    }

    public String getName() { return mName;}
    public String getTime() { return mTime;}
    public String getDescription() { return mDescription;}
    public String getEvent_Url() { return mEvent_Url;}
    public String getAddress() { return mAddress;}
    public String getState() { return mState;}
    public String getCity() { return mCity;}
    public String getZip() { return mZip;}
    public String getYes_rsvp_count() { return mYes_rsvp_count;}
    public String getMaybe_rsvp_count() { return mMaybe_rsvp_count;}
    public String getGroup_name() { return mGroup_name;}
    public String getWho() { return mWho;}

}
