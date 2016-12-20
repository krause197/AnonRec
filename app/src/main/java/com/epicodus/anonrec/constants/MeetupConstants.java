package com.epicodus.anonrec.constants;

import com.epicodus.anonrec.BuildConfig;

/**
 * Created by Guest on 12/2/16.
 */
public class MeetupConstants {
    public static final String MEETUP_TOKEN = BuildConfig.MEETUP_TOKEN;
    public static final String MEETUP_BASE_URL = "https://api.meetup.com/2/events?";
    public static final String MEETUP_KEY_QUERY_PARAMETER = "key";
    public static final String SOBER_QUERY_PARAMETER = "group_urlname";
    public static final String MEETUP_SIGN = "sign";
    public static final String FIREBASE_CHILD_EVENTS = "events";
    public static final String FIREBASE_QUERY_INDEX = "index";
    public static final String TOKEN_SHARED_PREFERENCE = "sobriety date";
    public static final String EXTRA_KEY_POSITION = "position";
    public static final String EXTRA_KEY_EVENTS = "events";
    public static final String KEY_SOURCE = "source";
    public static final String SOURCE_SAVED = "saved";
    public static final String SOURCE_FIND = "find";
}
