package com.corydominguez.tifftastic;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by coryd on 14/01/2014.
 */
public class ImageArrayAdapter extends ArrayAdapter<Image> {
    public ImageArrayAdapter(Context context, List<Image> images) {
        super(context, android.R.layout.simple_list_item_1, images);
    }
}
