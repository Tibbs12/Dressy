package com.closetkeeper.dressy.dto;


import java.io.File;
import java.util.List;


/******
 * used to create an entry for each saved article of clothing on the client program
 ******/
public class Item
{
    private String name;
    private List<String> tags;
    private File image;
    private File thumbnail;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }


    public File getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(File thumbnail) {
        this.thumbnail = thumbnail;
    }
}
