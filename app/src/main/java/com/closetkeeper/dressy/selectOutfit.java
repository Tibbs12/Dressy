package com.closetkeeper.dressy;



import static com.closetkeeper.dressy.home.Items;
import static com.closetkeeper.dressy.home.Outfits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class selectOutfit extends AppCompatActivity {

    private ImageButton outfitBackBtn;           /**Instantiating the ImageButtons*/
    private ImageButton addItemsBtn;
    private TextView outfitNameInput;
    private GridLayout gridLayout;
    private ImageButton outfitFwdBtn;
    //private ListView adapterView;

    /** Used for permissions to access camera*/
    public static final int RequestPermissionCode = 1;

    /** Need static variables to pass data using intent */
    public final static String OUTFITNAME = "OUTFITNAME";

    private String outfitName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_outfit);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);



       /* adapterView = (ListView) findViewById(R.id.adapterView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_select_outfit,Items);
        adapterView.setAdapter(adapter); */

        /** Sets up textview on selectCloset page, sets passed data into the textview */
        outfitNameInput = (TextView) findViewById(R.id.outfitNameInput);
        Intent intent = getIntent();
        outfitName = intent.getStringExtra(OUTFITNAME);
        outfitNameInput.setText(outfitName);


        /** setting variables equal to the xml button and creating OnClick listeners */



        outfitFwdBtn = (ImageButton) findViewById(R.id.outfitFwdBtn);
        outfitFwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Outfits.add(outfitName);
                createOutfit();
            }
        });




        /** End of OnClick Listeners */


        //For loop to display items
        for (Bitmap image : Items)
        {
            ImageView map = new ImageView(this);/** This code adds a button each time*/
            //map.setLayoutParams(gridLayout.getLayoutParams());
            map.setImageBitmap(image);
            gridLayout.addView(map);
        }
    }

    /** Empty method to prevent errors */
    public void createItem()
    {
        //MyItem Item = new Item(tag)
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 7); /** this is connected to "OnActivityResult" Method */
    }

    public void createOutfit() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.home.class);
        startActivity(intent);
    }

}