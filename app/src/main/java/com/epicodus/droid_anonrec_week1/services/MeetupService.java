package com.epicodus.droid_anonrec_week1.services;

import android.util.Log;

import com.epicodus.droid_anonrec_week1.MeetupConstants;
import com.epicodus.droid_anonrec_week1.models.Event;


import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 12/2/16.
 */
public class MeetupService {

    public static final String TAG = MeetupService.class.getSimpleName();
    public static final String groupName = "pdx-sober";

    public static void findEvents(String groupName, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(MeetupConstants.MEETUP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(MeetupConstants.MEETUP_KEY_QUERY_PARAMETER, MeetupConstants.MEETUP_TOKEN);
        urlBuilder.addQueryParameter(MeetupConstants.SOBER_QUERY_PARAMETER, groupName);
        urlBuilder.addQueryParameter(MeetupConstants.MEETUP_SIGN, "true");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Event> processResults(Response response) {
        ArrayList<Event> events = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            Log.v(TAG, "Events: "+ jsonData.toString());
            if(response.isSuccessful()){
                JSONObject eventsJSON = new JSONObject(jsonData);
                JSONArray listJSON = eventsJSON.getJSONArray("results");
                for (int i = 0; i < listJSON.length(); i++) {
                    JSONObject eventJSON = listJSON.getJSONObject(i);
                    String name = eventJSON.getString("name") + "";
                    String description = eventJSON.getString("description");
                    String time = eventJSON.getString("time") + "";
                    String event_url = eventJSON.getString("event_url");
                    String address = eventJSON.getJSONObject("venue").getString("address") + "";
                    String city = eventJSON.getJSONObject("venue").getString("city");
                    String state = eventJSON.getJSONObject("venue").getString("state");
                    String zip = eventJSON.getJSONObject("venue").getString("zip") + "";
                    String yes_rsvp_count = eventJSON.getString("yes_rsvp_count") + "";
                    String maybe_rsvp_count = eventJSON.getString("maybe_rsvp_count") + "";
                    String group_name = eventJSON.getJSONObject("group").getString("name");
                    String who = eventJSON.getJSONObject("group").getString("who");

                    Event event = new Event(name, description, time, event_url, address, city, state, zip, yes_rsvp_count, maybe_rsvp_count, group_name, who );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return events;
    }
}
