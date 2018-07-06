package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    public EditText tweet;
    private TextView mTweetCount;
    TwitterClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tweet = (EditText) findViewById(R.id.tvTweet);
        client = TwitterApp.getRestClient(this);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void SubmitTweet(View view) {
        String message = tweet.getText().toString();

        client.sendTweet(message, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Tweet tweet = Tweet.fromJSON(response);
                    // Prepare data intent
                    Intent data = new Intent();
                    // Pass tweet data back to timeLine
                    data.putExtra("tweet", Parcels.wrap(tweet));
                    // Activity finished ok, return the data®
                    setResult(RESULT_OK, data); // set result code and bundle data for response
                    finish(); // closes the activity, pass data to parent
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            //ADD FAILURES
        });
    }

}
