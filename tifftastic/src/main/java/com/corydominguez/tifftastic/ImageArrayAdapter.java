package com.corydominguez.tifftastic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.loopj.android.image.SmartImageView;

import java.util.List;

/**
 * Created by coryd on 14/01/2014.
 */
public class ImageArrayAdapter extends ArrayAdapter<Image> {
    public ImageArrayAdapter(Context context, List<Image> images) {
        super(context, R.layout.item_image_result, images);
    }

    @Override
    public SmartImageView getView(int position, View convertView, ViewGroup parent) {
        Image image = this.getItem(position);
        SmartImageView ivImage;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            ivImage = (SmartImageView) inflater.inflate(R.layout.item_image_result, parent, false);
        } else {
            ivImage = (SmartImageView) convertView;
            ivImage.setImageResource(android.R.color.transparent);
        }
        ivImage.setImageUrl(image.getTbUrl());
        return ivImage;

    }
}
