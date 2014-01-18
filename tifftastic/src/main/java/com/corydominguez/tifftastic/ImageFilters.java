package com.corydominguez.tifftastic;

import android.content.Intent;
import android.net.Uri;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by coryd on 18/01/2014.
 */
public class ImageFilters implements Serializable {
    private String size;
    private String color;
    private String type;
    private String site;
    private static final HashMap<String, Integer> spinnerIndex;
    static {
        // Careful! make sure spinner values reflect view string-arrays. This provides small
        // performance boost over creating array adapter and looping through values to find indices.
        spinnerIndex = new HashMap<String, Integer>();
        spinnerIndex.put("", 0);
        spinnerIndex.put("small", 1);
        spinnerIndex.put("medium", 2);
        spinnerIndex.put("large", 3);
        spinnerIndex.put("xlarge", 4);
        spinnerIndex.put("xxlarge", 5);
        spinnerIndex.put("huge", 6);
        spinnerIndex.put("icon", 7);
        spinnerIndex.put("black", 1);
        spinnerIndex.put("blue", 2);
        spinnerIndex.put("brown", 3);
        spinnerIndex.put("gray", 4);
        spinnerIndex.put("green", 5);
        spinnerIndex.put("orange", 6);
        spinnerIndex.put("pink", 7);
        spinnerIndex.put("purple", 8);
        spinnerIndex.put("red", 9);
        spinnerIndex.put("teal", 10);
        spinnerIndex.put("white", 11);
        spinnerIndex.put("yellow", 12);
        spinnerIndex.put("face", 1);
        spinnerIndex.put("photo", 2);
        spinnerIndex.put("clipart", 3);
        spinnerIndex.put("lineart", 4);
    }

    public ImageFilters() {
        this.size = "";
        this.color = "";
        this.type = "";
        this.site = "";
    }

    public ImageFilters(String size, String color, String type, String site) {
        this.size = size;
        this.color = color;
        this.type = type;
        this.site = site;
    }

    public Integer getSpinnerIndex(String value) {
        assert spinnerIndex.get(value) != null;
        return spinnerIndex.get(value);
    }

    public Integer getSizeIndex() {
        return spinnerIndex.get(getSize());
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getColorIndex() {
        return spinnerIndex.get(getColor());
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getTypeIndex() {
        return spinnerIndex.get(getType());
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        String filter  = "";
        if (!size.equals("")) filter += "&imgsz=" + getSize();
        if (!color.equals("")) filter += "&imgcolor=" + getColor();
        if (!type.equals("")) filter += "&imgtype=" + getType();
        if (!site.equals("")) filter += "&as_sitesearch" + Uri.encode(getSite());
        return filter;
    }
}
