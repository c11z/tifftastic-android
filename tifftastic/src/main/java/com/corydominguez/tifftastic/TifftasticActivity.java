package com.corydominguez.tifftastic;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    private ImageFilters imageFilters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tifftastic);
        setupViews();
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

        gvResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (page < 8 ) {
                    fetchImages(page, totalItemsCount);
                } else {
                    Log.d("DEBUG", "That's all folks");
                }
            }
        });
    }

    private void setupViews() {
        etQuery = (EditText) findViewById(R.id.etQuery);
        btSearch = (Button) findViewById(R.id.btSearch);
        gvResults = (GridView) findViewById(R.id.gvResults);
        Intent intent = getIntent();
        assert intent != null;
        imageFilters = (ImageFilters) intent.getSerializableExtra("filters");
        if (imageFilters == null) {
            imageFilters = new ImageFilters();
        }
    }

    public void onSearch(View v) {
        imageResults.clear();
        Toast.makeText(this, "Searching for " + etQuery.getText().toString(), Toast.LENGTH_SHORT).show();
        fetchImages(0, 0);

    }

    private void fetchImages(int page, int totalItemsCount) {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        assert etQuery.getText() != null;
        String query = etQuery.getText().toString();
        AsyncHttpClient http = new AsyncHttpClient();
        String url = "https://ajax.googleapis.com/ajax/services/search/images?" +
                "rsz=8&v=1.0&start=" + page*8 + "&q=" + Uri.encode(query) + imageFilters.toString();
        Log.d("DEBUG", "url=" + url);
        http.get(url, new JsonHttpResponseHandler() {
            @SuppressLint("NewApi")
            @Override
            public void onSuccess(int statusCode, Header[] headers, String json) {
                try {
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
        intent.putExtra("filters", imageFilters);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tifftastic, menu);
        return true;
    }
}
