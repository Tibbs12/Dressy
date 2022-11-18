package com.closetkeeper.dressy;


import static com.closetkeeper.dressy.home.Closets;
import static com.closetkeeper.dressy.home.Items;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.closetkeeper.dressy.databinding.ActivityMyItemsBinding;
import com.closetkeeper.dressy.databinding.ActivitySelectClosetBinding;
import com.closetkeeper.dressy.dto.Item;

import java.util.ArrayList;

public class selectCloset extends AppCompatActivity {

    ActivitySelectClosetBinding binding;
    private ImageButton closetBackBtn;           /**Instantiating the ImageButtons*/
    private ImageButton closetAddOutfits;
    private TextView closetNameInput;
    private ListView adapterView;
    private GridLayout itemsGrid;
    private AppCompatButton closetFwdBtn;

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
                    Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera, 7); /** this is connected to "OnActivityResult" Method */
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


        itemsGrid = findViewById(R.id.itemsGrid);


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
                Closets.add(closetName);
                createCloset();
            }
        });



        /** End of OnClick Listeners */


        //For loop to display items
        for (Item image : Items)
        {
            ImageView map = new ImageView(this);/** This code adds a button each time*/
            //map.setLayoutParams(gridLayout.getLayoutParams());
            map.setImageBitmap(image.getImage());
            itemsGrid.addView(map);
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


}