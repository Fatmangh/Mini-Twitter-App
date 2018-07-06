package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.User;

public class profileActivity extends AppCompatActivity {
    ImageView ivProfileImage;
    TextView userName;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        ivProfileImage = (ImageView) findViewById(R.id.tvProfilePic);
        userName = (TextView) findViewById(R.id.tvName);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivProfileImage.setClipToOutline(true);
        }

        User.getCurrentUser(context, new User.UserCallbackInterface() {
            @Override

            public void onUserAvailable(User currentUser) {
                Glide.with(context).load(currentUser.getProfileImageUrl()).into(ivProfileImage);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
