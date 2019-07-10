package com.mobileapps.flickr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.mobileapps.flickr.R;
import com.squareup.picasso.Picasso;

public class FullScreenActivity extends AppCompatActivity {

    ImageView imgFullScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        Intent intent = getIntent();
        String urlPicture = intent.getStringExtra("picture");

        imgFullScreen = findViewById(R.id.imgFullScreen);
        Picasso.with(this)
                .load(urlPicture)
                .into(imgFullScreen);

    }
}
