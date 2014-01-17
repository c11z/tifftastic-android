package com.corydominguez.tifftastic;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;

import java.io.IOException;
import java.util.ArrayList;


public class TifftasticActivity extends ActionBarActivity {
    private EditText etQuery;
    private Button btSearch;
    private GridView gvResults;
    private ArrayList<Image> imageResults = new ArrayList<Image>();
    private ImageArrayAdapter imageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tifftastic);
        setupViews();
        Intent intent = getIntent();
        imageAdapter = new ImageArrayAdapter(this, imageResults);
        gvResults.setAdapter(imageAdapter);
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), BigImageDisplayActivity.class);
                // get image at position i
                Image image = imageResults.get(i);
                intent.putExtra("image", image);
                startActivity(intent);
            }
        });
    }

    private void setupViews() {
        etQuery = (EditText) findViewById(R.id.etQuery);
        btSearch = (Button) findViewById(R.id.btSearch);
        gvResults = (GridView) findViewById(R.id.gvResults);
    }

    public void onSearch(View v) {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String query = etQuery.getText().toString();
        Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT).show();
        AsyncHttpClient http = new AsyncHttpClient();
        http.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&v=1.0&" +
                 "start=" + 0 + "&q=" + Uri.encode(query),
                new JsonHttpResponseHandler() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String json) {
                        try {
                            imageResults.clear();
                            GoogleImageAPI response = mapper.readValue(json, GoogleImageAPI.class);
                            imageAdapter.addAll(response.getResponseData().getResults());
                            Log.d("DEBUG", imageResults.toString());
                        } catch(JsonParseException e) {
                            e.printStackTrace();
                        } catch(JsonMappingException e) {
                            e.printStackTrace();
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void onSettings(MenuItem menuItem) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tifftastic, menu);
        return true;
    }
}
