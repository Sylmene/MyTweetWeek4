package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sylmene Anicet on 2/24/2018.
 */

public class User {
    // List the attributes
    public String name;
    public Long uid;
    public String screenName;
    public String profileImageUrl;

    public  String  tagLine;
    public int followersCount;
    public int followingCount;

    //deserialize the JSON
    public static User fromJSON(JSONObject json)throws JSONException{
        User user = new User();

        //extract and fill the values
        user.name = json.getString("name");
        user.uid = json.getLong("id");
        user.screenName =json.getString("screen_name");
        user.profileImageUrl = json.getString("profile_image_url");

        user.tagLine = json.getString("description");
        user.followersCount = json.getInt("followers_count");
        user.followingCount = json.getInt("friends_count");


        return user;
    }
}
