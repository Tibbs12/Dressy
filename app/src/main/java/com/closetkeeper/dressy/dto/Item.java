package com.closetkeeper.dressy.dto;


import android.graphics.Bitmap;

import java.io.File;
import java.util.List;


/******
 * used to create an entry for each saved article of clothing on the client program
 ******/
public class Item
{
    private String id;
    private List<String> tags;
    private Bitmap image;
    private File thumbnail;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }


    public File getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(File thumbnail) {
        this.thumbnail = thumbnail;
    }
}

