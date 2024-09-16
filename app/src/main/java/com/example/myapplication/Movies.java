package com.example.myapplication;

import android.widget.ImageView;

import java.io.Serializable;

public class Movies implements Serializable {
   String ImageUrl;
   String Title;
   String Desc;
   String longDesc;
   String VideoURL;


    public Movies(String imageUrl, String title, String desc, String longDesc, String videoURL) {
        ImageUrl = imageUrl;
        Title = title;
        Desc = desc;
        this.longDesc = longDesc;
        VideoURL = videoURL;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getVideoURL() {
        return VideoURL;
    }

    public void setVideoURL(String videoURL) {
        VideoURL = videoURL;
    }



    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
