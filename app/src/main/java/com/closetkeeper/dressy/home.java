package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.closetkeeper.dressy.databinding.ActivityHomeBinding;
import com.closetkeeper.dressy.dto.Item;
import com.closetkeeper.dressy.dto.Outfit;

import java.util.ArrayList;
import java.util.List;


public class home extends AppCompatActivity {

    ActivityHomeBinding binding;

    private ImageButton newclosetbtn;           /**Instantiating the two ImageButtons*/
    private ImageButton newoutfitbtn;
    private TextView calendarClick;
    private LinearLayout MyClosets;
    private LinearLayout MyOutfits;
    private ImageButton toCalendar;
    private ConstraintLayout home;
    public static Boolean hasAccountFile;

    /** list for each item, outfit, and closet */
    public static List<Item> Items = new ArrayList<Item>();  /** Items will have a bitmap and String "tag" */
    public static Item startingList[] = {};

    public static List<Outfit> Outfits = new ArrayList<Outfit>();
    public static String startOutfit[] = {};
                                                        /** Both of these list need to be changed to Objects */
    public static List<String> Closets = new ArrayList<String>();
    public static String startClosets[] = {};

    public static ArrayAdapter adapter;

    /** Used for permissions to access camera*/
    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //updateUI();

        //Retrieves the status of internal user data files (if they already exist or not) from the Access.java class
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            hasAccountFile = extras.getBoolean("userDataStatus");
        }

        MyClosets = (LinearLayout) findViewById(R.id.MyClosets);
        MyOutfits = (LinearLayout) findViewById(R.id.MyOutfits);
        home = (ConstraintLayout) findViewById(R.id.home);

        //For loop to display Outfits
        int x = 0;
        for (Outfit string : Outfits)
        {
            Button map = new Button(this);/** This code adds a button each time*/
            map.setLayoutParams(MyOutfits.getLayoutParams());
            map.setId(Outfits.indexOf(string));
            map.setText(Outfits.get(x).getName());
            MyOutfits.addView(map);
            createOnClick(map);
            x++;
        }

        //For loop to display Closets
        for (String closet : Closets)
        {
            ImageButton map = new ImageButton(this);/** This code adds a button each time*/
            map.setLayoutParams(MyClosets.getLayoutParams());
            //map.set(closet); This needs to be figured out still
            MyClosets.addView(map);
        }


        /** setting variables equal to the xml button and creating OnClick listeners */

        newclosetbtn = (ImageButton) findViewById(R.id.newclosetbtn);
        newclosetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCloset();
            }
        });

        newoutfitbtn = (ImageButton) findViewById(R.id.newoutfitbtn);
        newoutfitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOutfit();
            }
        });

        calendarClick = (TextView) findViewById(R.id.calendarClick);
        calendarClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar();
            }
        });

        toCalendar = (ImageButton) findViewById(R.id.toCalendar);
        toCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar();
            }
        });


        //Navigation menu code

        binding.bottomNavBar.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.homeNavBtn:

                    break;
                case R.id.closetNavBtn:
                    Intent mycloset = new Intent(this, com.closetkeeper.dressy.my_closets.class);
                    startActivity(mycloset);
                    break;
                case R.id.addNavBtn:
                    Intent items = new Intent(this, com.closetkeeper.dressy.my_items.class);
                    startActivity(items);
                    break;
                case R.id.searchNavBar:
                    /** Intent search = new Intent(this, com.closetkeeper.dressy.my_closets.class);
                     startActivity(search); */
                    break;
                case R.id.outfitsNavBtn:
                    Intent outfit = new Intent(this, com.closetkeeper.dressy.my_outfits.class);
                    startActivity(outfit);
                    break;
            }


            return true;
        });

        /** End of OnClick Listeners */
    }

    /** Start of methods createCloset and createOutfit */

    public void createCloset() {
      //  ImageButton closet = new ImageButton(this);         /** This code adds a button each time*/
      //  closet.setLayoutParams(MyClosets.getLayoutParams());       /** we create a new closet */
      //  MyClosets.addView(closet);
        Intent intent = new Intent(this, com.closetkeeper.dressy.createCloset.class);
        startActivity(intent);
    }

     public void createOutfit() {
        /*ImageButton outfit = new ImageButton(this);         /** This code adds a button each time*/
     /*   outfit.setLayoutParams(MyOutfits.getLayoutParams());       /** we create a new outfit */
      /*  MyOutfits.addView(outfit);
      Updated since last use. NOT NEEDED FOR NOW*/
        Intent intent = new Intent(this, com.closetkeeper.dressy.createOutfit.class);
        startActivity(intent);
    }

    public void openCalendar() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.calendar.class);
        startActivity(intent);
    }

    /** this method creates an onclick listener for every button that calls this method. */
    public void createOnClick(Button map){
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String outfitIndex;
                outfitIndex = Integer.toString(v.getId());
                openCanvas(outfitIndex);            /** need to pass outfit to canvas page */
            }
        });
    }

    public void openCanvas(String outfitIndex) {
        Intent intent = new Intent(this, com.closetkeeper.dressy.outfit_canvas.class);

        /** This putExtra function passes the actual string to the new page */
        intent.putExtra(outfit_canvas.INDEX, outfitIndex);


        startActivity(intent);      //Take in user input from this XML sheet and pass it to the new page
    }

    public void writeOutfit(ImageButton map, int x) {
        TextView view = new TextView(home.this);
        view.setText(Outfits.get(x).getName());
        view.setLayoutParams(map.getLayoutParams());
        view.setPaddingRelative(map.getPaddingStart(),map.getPaddingTop(),map.getPaddingEnd(),map.getPaddingBottom());
        home.addView(view);



    }


}