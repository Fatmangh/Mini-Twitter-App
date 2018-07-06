package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;

import org.parceler.Parcels;

public class DetailsActivity extends AppCompatActivity {

    public Tweet tweet;
    TextView tweeter;
    TextView tweeter1;
    TextView tweeter2;
    Context context;
    ImageView ivProfileImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tweeter = (TextView) findViewById(R.id.textView);
        tweeter1 = (TextView) findViewById(R.id.tvName);
        tweeter2 = (TextView) findViewById(R.id.textView3);
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        tweeter.setText(tweet.body);
        tweeter1.setText(tweet.user.screenName);
        tweeter2.setText(tweet.user.name);

        context = this;

        ivProfileImage = (ImageView) findViewById(R.id.pic);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivProfileImage.setClipToOutline(true);
        }

        User.getCurrentUser(context, new User.UserCallbackInterface() {
            @Override
            public void onUserAvailable(User currentUser) {
                Glide.with(context).load(tweet.user.getProfileImageUrl()).into(ivProfileImage);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
