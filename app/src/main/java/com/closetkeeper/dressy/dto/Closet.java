package com.closetkeeper.dressy.dto;

import java.util.*; //Importing all java.util to help support List programming

/**
 * The Closet class holds the name of the closet, a brief description of the closet, a List of items included in this closet, and a List of outfits included
 * in this closet. The list set functions will replace the current list with a new list of items or outfits.
 *
 *Created by Matt on 10/11/22
 */

public class Closet
{
    // Declaring variables of type String: name, description, and of type List: items, outfits.
    private String name;
    private String description;
    private List<String>[] items;           //List items will contain type item TODO: replace type String with type Item/Items
    private List<String>[] outfits;         //List outfits will contain type outfit TODO: replace type String with type Outfit

    //returns String name
    public String getName() {  //gets value of String name
        return name;
    }

    //Sets String name
    public void setName(String name) { //sets value of String name
        this.name = name;
    }

    //returns String description
    public String getDescription() {  //gets value of String description
        return description;
    }

    //sets String description
    public void setDescription(String description) { //sets value of String description
        this.description = description;
    }

    //returns List of Items named items
    public List<String>[] getItems() { //gets value of String description
        return items;
    }

    //sets List of Items into List<Items> items
    public void setItems(List<String>[] items) { //sets List of Items
        this.items = items;
    }

    //returns List of Outfits named outfits
    public List<String>[] getOutfits() { //gets List of Outfits
        return outfits;
    }

    //sets List of Outfits into List<Outfit> outfits
    public void setOutfits(List<String>[] outfits) { //sets List of Outfits
        this.outfits = outfits;
    }
}
