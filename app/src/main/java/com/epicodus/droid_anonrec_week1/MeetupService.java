package com.epicodus.droid_anonrec_week1;

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
}
