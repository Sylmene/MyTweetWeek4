package com.codepath.apps.restclienttemplate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Sylmene Anicet on 3/3/2018.
 */

public class HomeTimelineFragment extends TweetsListFragment {

    TwitterClient client;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        client = TwitterApp.getRestClient();
        populateTimeLine();
    }
    private void populateTimeLine() {
        client.getHomeTimeLine( new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d( "TwitterClient", response.toString() );
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //             Log.d("TwitterClient", response.toString());
                // iterate through the JSON array
                // for each entry, deserialize the JSON object
               addItems( response );
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d( "TwitterClient", responseString );
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d( "TwitterClient", errorResponse.toString() );
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d( "TwitterClient", errorResponse.toString() );
                throwable.printStackTrace();
            }
        });
    }
}

