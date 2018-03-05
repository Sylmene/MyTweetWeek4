package com.codepath.apps.restclienttemplate.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TweetAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Sylmene Anicet on 3/3/2018.
 */

public class TweetsListFragment extends Fragment implements TweetAdapter.TweetAdapterListener {

    public  interface TweeSelectedListener{
        //handle tweet selection
        public void onTweetSelected(Tweet tweet);
    }

    TweetAdapter tweetAdapter;
    ArrayList<Tweet> tweets;
    RecyclerView rvTweets;
    //inflation happens inside onCreateView


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate the layout
        View v = inflater.inflate( R.layout.fragments_tweets_list,container,false);
        //find the RecyclerView
        rvTweets = (RecyclerView) v.findViewById(R.id.rvTweet);
        //init the arraylist(data source)
        tweets = new ArrayList<>();
        //construct the adapter from this datasource
        tweetAdapter =new TweetAdapter(tweets, this);
        //RecyclerView setup(layout manager, use adapter)
        rvTweets.setLayoutManager(new LinearLayoutManager(getContext()));
        //set the adapter
        rvTweets.setAdapter(tweetAdapter);
        return  v;
    }
    public void  addItems(JSONArray response){
        try {

            for (int i = 0; i < response.length(); i++) {
                // convert each object to a Tweet
                // add that Tweet model to our data source
                // notify the adapter thet we've added an item
                Tweet tweet = Tweet.fromJSON( response.getJSONObject( i ) );
                tweets.add( tweet );
                tweetAdapter.notifyItemInserted( tweets.size() - 1 );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(View view, int position) {
      Tweet tweet = tweets.get(position);
        ((TweeSelectedListener) getActivity()).onTweetSelected(tweet);
    }

}
