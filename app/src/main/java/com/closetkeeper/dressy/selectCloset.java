package com.closetkeeper.dressy;


import static com.closetkeeper.dressy.home.Closets;
import static com.closetkeeper.dressy.home.Items;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;

public class selectCloset extends AppCompatActivity {

    private ImageButton closetBackBtn;           /**Instantiating the ImageButtons*/
    private ImageButton closetAddOutfits;
    private TextView closetNameInput;
    private ListView adapterView;
    private GridLayout itemsGrid;
    private ImageButton closetFwdBtn;

    /** Need static variables to pass data using intent */
    public final static String CLOSETNAME = "CLOSETNAME";

    private String closetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_closet);

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



        closetFwdBtn = (ImageButton) findViewById(R.id.closetFwdBtn);
        closetFwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Closets.add(closetName);
                createCloset();
            }
        });



        /** End of OnClick Listeners */


        //For loop to display items
        for (Bitmap image : Items)
        {
            ImageView map = new ImageView(this);/** This code adds a button each time*/
            //map.setLayoutParams(gridLayout.getLayoutParams());
            map.setImageBitmap(image);
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