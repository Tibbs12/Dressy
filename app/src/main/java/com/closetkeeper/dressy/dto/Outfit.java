package com.closetkeeper.dressy.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds important data like name, all tags associated, and the items that make up this outfit. Holds functions that allow
 * for the editing and removal of items, plus more. The secondary constructor is used to set existing data about an outfit.
 *
 * <br>
 * <br>Created by Tim on 10/30/2022.
 * <br>Last Modified on 11/1/2022.
 */
public class Outfit {

    private String name;
    private List<String> tags;
    private List<String> items; //ToDo: Change String data type to Item

    private final String NULL_NAME = "Not Named";

    public Outfit(){
        tags = new ArrayList<>();
        items = new ArrayList<>();
    }


    /**
     * This constructor is used when an Outfit already exist for a user.
     * @param name The pre-existing Outfit name from database.
     * @param tags The pre-existing tags from database.
     * @param items The pre-existing Items from database.
     */
    public Outfit(String name, List<String> tags, List<String> items){  //ToDo: Change List<String> items to List<Item> items
        this.name = name;
        this.tags = tags;
        this.items = items;
    }


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


    /**
     * Sets Outfit name.
     * @param name String name of the outfit.
     */
    public void setName(String name) {
        //Check to see if outfit name is null or empty
        if(name == null || name.trim().isEmpty()){
            this.name = NULL_NAME;
        }
        else
        {
            this.name = name;
        }
    }


    /**
     * Returns all the tags associated with this Outfit.
     * @return List<String> of tags belonging to this outfit.
     */
    public List<String> getTags() {
        //Check to see if outfit tags is empty or null
        if(tags.isEmpty() || tags == null){
            return null;
        }
        else{
            return tags;
        }
    }


    /**
     * Sets all the tag names associated with this Outfit.
     * @param tags List<String> tags of the outfit.
     */
    public void setTags(List<String> tags) {
        //Check to see if tags parameter is empty or null
        if(tags != null && !tags.isEmpty()){
            this.tags = tags;
        }
    }


    /**
     * Returns a single tag associated with this Outfit using the index number of the List.
     * @param tagIndex The index value of the specific tag you wish to return.
     * @return String of single tag specified by parameter.
     */
    public String getSingleTag(int tagIndex){
        //Check to see if tagIndex is empty and in bounds of the List
        if((tagIndex >= 0 && tagIndex < this.getTagsLength()) && !tags.get(tagIndex).isEmpty()){
            return tags.get(tagIndex);
        }
        else
        {
            return null;
        }
    }


    /**
     * Adds an additional tag to the List associated with this Outfit.
     * @param tagName String tag name.
     */
    public void addTag(String tagName){
        //Check to see if tagName is empty or null
        if(tagName != null && !tagName.trim().isEmpty()){
            tags.add(tagName);
        }
    }


    /**
     * Edits the name of an existing tag associated with this Outfit.
     * @param tagIndex The int index value of the specific tag wished to be edited.
     * @param newName String new tag name.
     */
    public void editTag(int tagIndex, String newName){
        //Check to see if tagIndex is within the List bounds and actually has data. Then checks if newName is not null or empty
        if((tagIndex >= 0 && tagIndex < this.getTagsLength()) && (!tags.get(tagIndex).isEmpty()) && (newName != null && !newName.trim().isEmpty()))
        {
            tags.set(tagIndex, newName);
        }
    }


    /**
     * Removes a specific tag that is associated with this Outfit.
     * @param tagIndex The int index value of the specific tag wished to be removed.
     */
    public void removeTag(int tagIndex){
        //Check to see if tagIndex is within the List bounds and actually has data
        if((tagIndex >= 0 && tagIndex < this.getTagsLength()) && !tags.remove(tagIndex).isEmpty()){
            tags.remove(tagIndex);
        }
    }


    /**
     * Clears all the tags associated with this Outfit.
     */
    public void clearTags(){
        //Check to see if the tags List is empty
        if(!tags.isEmpty() && tags != null){
            tags.clear();
        }
    }


    /**
     * Returns the total amount of tags associated with this Outfit.
     * @return int the length (total amount) of tags.
     */
    public int getTagsLength(){
        return tags.size();
    }


    /**
     * Returns List of Items the Outfit is consisted of.
     * @return List<Item> associated with this outfit.
     */
    public List<String> getItems() {  //ToDo: Change List<String> to List<Item>
        //Check to see if the items List is empty, if so return null
        if(!items.isEmpty() && items != null){
            return items;
        }
        else{
            return null;
        }
    }


    /**
     * Sets the Outfit's Items List.
     * @param items List of type Item.
     */
    public void setItems(List<String> items) {  //ToDo: Change List<String> to List<Item>
        //Check to see if items parameter is empty
        if(!items.isEmpty() && items != null){
            this.items = items;
        }
    }


    /**
     * Returns a single Item associated with this Outfit using the index number of the List.
     * @param itemIndex The index value of the specific Item you wish to return.
     * @return Item specified by parameter that belongs to this outfit.
     */
    public String getSingleItem(int itemIndex){  //ToDo: Change return type from String to Item
        //Check to see if itemIndex is empty
        if(!items.get(itemIndex).isEmpty()){
            return items.get(itemIndex);
        }
        else
        {
            return null;
        }
    }


    /**
     * Adds an additional tag to the List associated with this Outfit.
     * @param newItem The new Item being added to the outfit.
     */
    public void addItem(String newItem){  //ToDo: Change String parameter data type to Item
        //Check to see if itemName is empty
        if(newItem != null && !newItem.trim().isEmpty()){
            items.add(newItem);
        }
    }


    /**
     * Changes an existing Item associated with this Outfit to the one passed using this function.
     * @param itemIndex The int index value of the specific Item wished to be replaced.
     * @param newItem The new Item.
     */
    public void changeItem(int itemIndex, String newItem){  //ToDo: Change String parameter data type to Item
        //Check to see if itemIndex is within the List bounds and actually has data. Then checks if newItem is not null or empty
        if((itemIndex >= 0 && itemIndex < this.getItemsLength()) && (!items.get(itemIndex).isEmpty()) && (newItem != null && !newItem.trim().isEmpty()))
        {
            items.set(itemIndex, newItem);
        }
    }


    /**
     * Removes a specific Item that is associated with this Outfit.
     * @param itemIndex The int index value of the specific Item wished to be removed.
     */
    public void removeItem(int itemIndex){
        //Check to see if itemIndex is within the List bounds and actually has data
        if((itemIndex >= 0 && itemIndex < this.getItemsLength()) && !items.remove(itemIndex).isEmpty()){
            items.remove(itemIndex);
        }
    }


    /**
     * Clears all the Items associated with this Outfit.
     */
    public void clearItems(){
        //Check to see if the items List is empty
        if(!items.isEmpty()){
            items.clear();
        }
    }


    /**
     * Returns the total amount of Items associated with this Outfit.
     * @return int the length (total amount) of Items.
     */
    public int getItemsLength(){
        return items.size();
    }
}
