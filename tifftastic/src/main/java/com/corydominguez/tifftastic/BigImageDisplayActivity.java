package com.corydominguez.tifftastic;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.image.SmartImageView;

public class BigImageDisplayActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image_display);
        Image image = (Image) getIntent().getSerializableExtra("image");
        SmartImageView ivBigImage = (SmartImageView) findViewById(R.id.ivBigImage);
        ivBigImage.setImageUrl(image.getUrl());
    }
}
