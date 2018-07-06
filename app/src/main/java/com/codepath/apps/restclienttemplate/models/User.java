package com.codepath.apps.restclienttemplate.models;

import android.content.Context;

import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import cz.msebera.android.httpclient.Header;

@Parcel
public class User {
    //list of attributes
    public String name;
    public String uid;
    public String screenName;
    public String profileImageUrl;
    public boolean isVerified;
    //deserialize the JSON
    public static User fromJSON(JSONObject json) throws JSONException {
        User user = new User();

        //extracts and fill the values
        user.name = json.getString("name");
        user.uid = json.getString("id");
        user.screenName = json.getString("screen_name");
        user.profileImageUrl = json.getString("profile_image_url");
        user.isVerified = json.getBoolean("verified");

        return user;
    }

    public boolean isVerified(){ return isVerified; }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }


    public interface UserCallbackInterface {
        void onUserAvailable(User currentUser);
    }

    public static void getCurrentUser(Context context, final UserCallbackInterface handler) {
        TwitterClient client = TwitterApp.getRestClient(context);
        client.verifyCredentials(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    handler.onUserAvailable(User.fromJSON(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
