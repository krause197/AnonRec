package com.epicodus.droid_anonrec_week1.services;

import android.util.Log;

import com.epicodus.droid_anonrec_week1.MeetupConstants;
import com.epicodus.droid_anonrec_week1.models.Event;
import com.example.guest.weatherclass.Constants;
import com.example.guest.weatherclass.models.Forcast;
import com.example.guest.weatherclass.models.Weather;

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
                JSONArray listJSON = eventsJSON.getJSONArray("list");
                for (int i = 0; i < listJSON.length(); i++) {
                    JSONObject eventJSON = listJSON.getJSONObject(i);
                    String placeholder =
                    String placeholder2 =

                    Event event = new Event(placeholder, placeholder2);
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
