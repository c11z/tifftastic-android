package com.corydominguez.tifftastic;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Spinner;

public class SettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onSaveSettings(View v) {
        Spinner imageSize = (Spinner) findViewById(R.id.spImageSize);
        Intent intent = new Intent(getApplicationContext(), TifftasticActivity.class);
        intent.putExtra("imageSize", imageSize.getSelectedItem().toString());
        startActivity(intent);
    }
}
