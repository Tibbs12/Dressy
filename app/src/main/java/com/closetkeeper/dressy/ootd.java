package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.closetkeeper.dressy.dto.Item;
import com.closetkeeper.dressy.dto.Outfit;

import java.util.ArrayList;
import java.util.List;

public class ootd extends AppCompatActivity {

    private HorizontalScrollView scrollView;
    private LinearLayout ootdLayout;

    public final static String OUTFIT = "0";
    public final static String DATE = "---";
    private int index;
    private String date;

    private TextView textView4;


    public static List<ImageView> calendarViewList = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ootd);

        Intent i = getIntent();
        index = Integer.parseInt(i.getStringExtra(OUTFIT));
        date = i.getStringExtra(DATE);

        Outfit myOutfit = calendar.calendarOutfits.get(index);

        textView4 = (TextView)findViewById(R.id.textView4);
        textView4.setText(date);

        scrollView = (HorizontalScrollView)findViewById(R.id.scrollView);
        ootdLayout = (LinearLayout)findViewById(R.id.ootdLayout);

        calendarViewList.removeAll(calendarViewList);
        if (myOutfit.getItemsLength() > 0)
        {
            for (Item image : myOutfit.getItems()) {
                ImageView map = new ImageView(this);/** This code adds a button each time*/

                map.setImageBitmap(image.getImage());
                map.setClickable(true);
                map.setPadding(18, 18, 18, 18);
                map.setId(myOutfit.getItems().indexOf(image));
                map.setSelected(false);
                ootdLayout.addView(map);
                //createOnLongClick(map);
                calendarViewList.add(map);
            }
        }

    }
}