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
    private List<Item>[] items;           //List items will contain type item
    private ArrayList<Outfit> outfits;//List outfits will contain type outfit TODO: replace type String with type Outfit

    private final String NULL_NAME = "Not Named";

    public Closet(){
        outfits = new ArrayList<Outfit>();
    }

    //returns String name
    /**
     * Returns Outfit name. If String is null or empty, then the function will return the
     * String "Not Named".
     * @return String of outfit's name.
     */
    public String getName() {
        //Check to see if outfit name is null or empty
        if(name == null || name.trim().isEmpty()){
            return NULL_NAME;
        }
        else {
            return name;
        }
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
    public List<Item>[] getItems() { //gets value of String description
        return items;
    }

    //sets List of Items into List<Items> items
    public void setItems(List<Item>[] items) { //sets List of Items
        this.items = items;
    }

    //returns List of Outfits named outfits
    public ArrayList<Outfit> getOutfits() { //gets List of Outfits
        return outfits;
    }

    //sets List of Outfits into List<Outfit> outfits
    public void setOutfits(ArrayList<Outfit> outfits) { //sets List of Outfits
        this.outfits = outfits;
    }

    /**
     * Adds an additional tag to the List associated with this Outfit.
     * @param newOutfit The new Item being added to the outfit.
     */
    public void addOutfit(Outfit newOutfit){
        //Check to see if itemName is empty
        //if(newItem != null && !newItem.getId().trim().isEmpty()){
        outfits.add(newOutfit);
        //}
    }

    public void removeOutfit(int outfitIndex){
        outfits.remove(outfitIndex);
    }

    public int getOutfitsLength(){
        return outfits.size();
    }
}
