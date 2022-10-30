package com.closetkeeper.dressy.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds important data like name, all tags associated, and the items that make up this outfit. When the user registers
 * for the first time, the basic class constructor will automatically create an account ID for the user. If the user already has
 * an account, then the secondary constructor needs to be executed allowing this class to receive the existing user's data.
 *
 * <br>
 * <br>Create by Tim on 10/30/2022.
 */
public class Outfit {

    private String name;
    private List<String> tags;
    private List<String> items; //ToDo: Change String data type to Item


    public Outfit(){
        tags = new ArrayList<>();
        items = new ArrayList<>();
    }


    /**
     * This constructor is used when an Outfit already exist for an user.
     * @param name The pre-existing Outfit name from live database.
     * @param tags The pre-existing tags from live database.
     * @param items The pre-existing Items from live database.
     */
    public Outfit(String name, List<String> tags, List<String> items){
        this.name = name;
        this.tags = tags;
        this.items = items;
    }


    /**
     * Returns Outfit name.
     * @return String.
     */
    public String getName() {
        if(name == null)
        {
            return "Not Named";
        }
        else {
            return name;
        }
    }


    /**
     * Sets Outfit name.
     * @param name String name of the outfit.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Returns all the tags associated with this Outfit.
     * @return List<String>.
     */
    public List<String> getTags() {
        return tags;
    }


    /**
     * Sets all the tag names associated with this Outfit.
     * @param tags List<String> tags of the outfit.
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    /**
     * Returns a single tag associated with this Outfit using the index number of the List.
     * @param tagIndex The index value of the specific tag you wish to return.
     * @return String.
     */
    public String getSingleTag(int tagIndex){
        return tags.get(tagIndex);
    }


    /**
     * Adds an additional tag to the List associated with this Outfit.
     * @param tagName String tag name.
     */
    public void addTag(String tagName){
        tags.add(tagName);
    }


    /**
     * Edits the name of an existing tag associated with this Outfit.
     * @param tagIndex The int index value of the specific tag wished to be edited.
     * @param newName String new tag name.
     */
    public void editTag(int tagIndex, String newName){
        tags.set(tagIndex, newName);
    }


    /**
     * Removes a specific tag that is associated with this Outfit.
     * @param tagIndex The int index value of the specific tag wished to be removed.
     */
    public void removeTag(int tagIndex){
        tags.remove(tagIndex);
    }


    /**
     * Clears all the tags associated with this Outfit.
     */
    public void clearTags(){
        tags.clear();
    }


    /**
     * Returns the total amount of tags associated with this Outfit.
     * @return int.
     */
    public int getTagsLength(){
        return tags.size();
    }


    /**
     * Returns List of Items the Outfit is consisted of.
     * @return List of Items.
     */
    public List<String> getItems() {
        return items;
    }


    /**
     * Sets Outfit's Items List.
     * @param items List of type Item.
     */
    public void setItems(List<String> items) {
        this.items = items;
    }


    /**
     * Returns a single Item associated with this Outfit using the index number of the List.
     * @param itemIndex The index value of the specific Item you wish to return.
     * @return Item.
     */
    public String getSingleItem(int itemIndex){  //ToDo: Change return type from String to Item
        return items.get(itemIndex);
    }


    /**
     * Adds an additional tag to the List associated with this Outfit.
     * @param newItem The new Item being added to the outfit.
     */
    public void addItem(String newItem){  //ToDo: Change String parameter data type to Item
        items.add(newItem);
    }


    /**
     * Changes an existing Item associated with this Outfit to the one passed using this function.
     * @param itemIndex The int index value of the specific Item wished to be replaced.
     * @param newItem The new Item.
     */
    public void changeItem(int itemIndex, String newItem){  //ToDo: Change String parameter data type to Item
        items.set(itemIndex, newItem);
    }


    /**
     * Removes a specific Item that is associated with this Outfit.
     * @param itemIndex The int index value of the specific Item wished to be removed.
     */
    public void removeItem(int itemIndex){
        items.remove(itemIndex);
    }


    /**
     * Clears all the Items associated with this Outfit.
     */
    public void clearItems(){
        items.clear();
    }


    /**
     * Returns the total amount of Items associated with this Outfit.
     * @return int.
     */
    public int getItemsLength(){
        return items.size();
    }
}
