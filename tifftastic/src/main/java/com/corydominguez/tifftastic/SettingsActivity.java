package com.corydominguez.tifftastic;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingsActivity extends ActionBarActivity {
    private ImageFilters imageFilters;
    private Spinner spImageSize;
    private Spinner spImageColor;
    private Spinner spImageType;
    private EditText etImageSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setUpViews();
    }

    private void setUpViews() {
        Intent intent = getIntent();
        imageFilters = (ImageFilters) intent.getSerializableExtra("filters");
        spImageSize = (Spinner) findViewById(R.id.spImageSize);
        spImageColor = (Spinner) findViewById(R.id.spImageColor);
        spImageType = (Spinner) findViewById(R.id.spImageType);
        etImageSite = (EditText) findViewById(R.id.etImageSite);
        spImageSize.setSelection(imageFilters.getSizeIndex());
        spImageColor.setSelection(imageFilters.getColorIndex());
        spImageType.setSelection(imageFilters.getTypeIndex());
        etImageSite.setText(imageFilters.getSite());
    }

    public void onSaveSettings(View v) {
        assert spImageSize.getSelectedItem() != null;
        assert spImageColor.getSelectedItem() != null;
        assert spImageType.getSelectedItem() != null;
        assert etImageSite.getText() != null;
        imageFilters.setSize(spImageSize.getSelectedItem().toString());
        imageFilters.setColor(spImageColor.getSelectedItem().toString());
        imageFilters.setType(spImageType.getSelectedItem().toString());
        imageFilters.setSite(etImageSite.getText().toString());
        Intent intent = new Intent(getApplicationContext(), TifftasticActivity.class);
        intent.putExtra("filters", imageFilters);
        startActivity(intent);
    }
}
