package com.closetkeeper.dressy.dto;

import java.text.DateFormat;
import java.util.Date;

/**
 * The Calendar class holds outfits on specific days of the calendar. User's will be able to edit days of the calendar to add or remove outfits from said days.
 * The get and set functions for our calendar will have to directly communicate with the XML on our application.
 * Maybe we can do a list of outfits for each day of the calendar and append or remove outfits from each list for each day???
 *
 * Created by Matt on 10/22/22
 */

public class Calendar {   /*This class will need to extend or implement the Outfit class */
    private Date date;
    private DateFormat format;

    /* getters and setters for date below */

    public Date getDate()
    {
        return date;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }

    //getters and setters for format

    public DateFormat getFormat()
    {
        return format;
    }
    public void setFormat(DateFormat format)
    {
        this.format = format;
    }


    public Date getMonth()
    {
        return month;  /*This will be changed to whatever XML reference*/
    }

    public Date getDay()
    {
        return day; /*Change to XML reference for day*/
    }

    /* an add outfit function that will add an outfit to a day specified. (variable x being the int day of the calendar)*/
    public void addOutfit(Outfit outfit, int x) {

    }

    /* does the same thing as add outfit, takes in an outfit and int of the day, then removes the outfit from that day*/
    public void removeOutfit(Outfit outfit, int x) {

    }
}
