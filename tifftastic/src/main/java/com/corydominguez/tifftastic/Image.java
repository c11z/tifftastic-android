package com.corydominguez.tifftastic;

import java.io.Serializable;

/**
 * Created by coryd on 13/01/2014.
 */
public class Image implements Serializable {

    private String title;
    private String titleNoFormatting;
    private String url;
    private String tbUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleNoFormatting() {
        return titleNoFormatting;
    }

    public void setTitleNoFormatting(String titleNoFormatting) {
        this.titleNoFormatting = titleNoFormatting;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTbUrl() {
        return tbUrl;
    }

    public void setTbUrl(String tbUrl) {
        this.tbUrl = tbUrl;
    }

    @Override
    public String toString() {
        return "Image{" +
                "titleNoFormatting='" + titleNoFormatting + '\'' +
                ", url='" + url + '\'' +
                ", tbUrl='" + tbUrl + '\'' +
                '}';
    }

}
