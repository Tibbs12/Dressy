package com.closetkeeper.dressy;


import static com.closetkeeper.dressy.home.Closets;
import static com.closetkeeper.dressy.home.Items;
import static com.closetkeeper.dressy.home.Outfits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import com.closetkeeper.dressy.databinding.ActivityMyItemsBinding;
import com.closetkeeper.dressy.databinding.ActivitySelectClosetBinding;
import com.closetkeeper.dressy.dto.Closet;
import com.closetkeeper.dressy.dto.Item;
import com.closetkeeper.dressy.dto.Outfit;

import java.util.ArrayList;
import java.util.List;

public class selectCloset extends AppCompatActivity {

    ActivitySelectClosetBinding binding;
    private ImageButton closetBackBtn;           /**Instantiating the ImageButtons*/
    private ImageButton closetAddOutfits;
    private TextView closetNameInput;
    private ListView adapterView;
    private AppCompatButton closetFwdBtn;
    private LinearLayout OutfitsScroll;

    public static List<TextView> closetViewList = new ArrayList<TextView>();

    /** Need static variables to pass data using intent */
    public final static String CLOSETNAME = "CLOSETNAME";

    private String closetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectClosetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Navigation menu code
        binding.bottomNavBar.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.homeNavBtn:
                    Intent intent = new Intent(this, com.closetkeeper.dressy.home.class);
                    startActivity(intent);
                    break;
                case R.id.closetNavBtn:
                    Intent closet = new Intent(this, com.closetkeeper.dressy.my_closets.class);
                    startActivity(closet);
                    break;
                case R.id.addNavBtn:
                    Intent items = new Intent(this, com.closetkeeper.dressy.my_items.class);
                    startActivity(items);
                    break;
                case R.id.searchNavBar:
                    break;
                case R.id.outfitsNavBtn:
                    Intent outfit = new Intent(this, com.closetkeeper.dressy.my_outfits.class);
                    startActivity(outfit);
                    break;
            }


            return true;
        });


        OutfitsScroll = (LinearLayout) findViewById(R.id.OutfitsScroll);


       /* adapterView = (ListView) findViewById(R.id.adapterView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_select_outfit,Items);
        adapterView.setAdapter(adapter); */

        /** Sets up textview on selectCloset page, sets passed data into the textview */
        closetNameInput = (TextView) findViewById(R.id.closetNameInput);
        Intent intent = getIntent();
        closetName = intent.getStringExtra(CLOSETNAME);
        closetNameInput.setText(closetName);


        /** setting variables equal to the xml button and creating OnClick listeners */



        closetFwdBtn = (AppCompatButton) findViewById(R.id.closetFwdBtn);
        closetFwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Closet MyCloset = new Closet(); //Create a new outfit on click
                MyCloset.setName(closetName);

                for (TextView view :  closetViewList)
                {
                    if (view.isSelected()){
                        MyCloset.addOutfit(Outfits.get(view.getId()));  //for every item selected, add outfit to new closet
                    }
                }
                Closets.add(MyCloset); //after loop, add new outfit to list of outfits
                createCloset(); //open home page
            }
        });



        /** End of OnClick Listeners */


        //For loop to display items
        closetViewList.removeAll(closetViewList);
        for (Outfit image : Outfits)
        {
            TextView map = new TextView(this);/** This code adds a button each time*/
            map.setText(image.getName());
            map.setClickable(true);
            map.setPadding(18, 18, 18, 18);
            map.setId(Outfits.indexOf(image));
            map.setSelected(false);
            closetViewList.add(map);
            OutfitsScroll.addView(map);
            createOnLongClick(map);
        }
    }


    /** Method that changes page to createOutfit page */
    public void createOutfit() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.createOutfit.class);
        startActivity(intent);
    }

    public void createCloset() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.home.class);
        startActivity(intent);
    }


    /** this method creates an onlongclick listener for every button that calls this method. */
    public void createOnLongClick(TextView map){
        map.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if (map.isSelected() != true)
                {
                    map.setAllCaps(true);
                    map.setSelected(true);

                }
                else { //isSelected() is true
                    map.setAllCaps(false);
                    map.setSelected(false);
                    //map.setBackgroundColor(getResources().getColor(R.color.purple));
                }
                return true;
            }
        });
    }


}