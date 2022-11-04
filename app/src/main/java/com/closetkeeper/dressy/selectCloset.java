package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class selectCloset extends AppCompatActivity {

    private ImageButton closetBackBtn;           /**Instantiating the ImageButtons*/
    private ImageButton closetAddOutfits;
    private TextView closetNameInput;

    /** Need static variables to pass data using intent */
    public final static String CLOSETNAME = "CLOSETNAME";

    private String closetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_closet);

        /** Sets up textview on selectCloset page, sets passed data into the textview */
        closetNameInput = (TextView) findViewById(R.id.closetNameInput);
        Intent intent = getIntent();
        closetName = intent.getStringExtra(CLOSETNAME);
        closetNameInput.setText(closetName);


        /** setting variables equal to the xml button and creating OnClick listeners */

        closetBackBtn = (ImageButton) findViewById(R.id.closetBackBtn);
        closetBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        closetAddOutfits = (ImageButton) findViewById(R.id.closetAddOutfits);
        closetAddOutfits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOutfit();
            }
        });

        /** End of OnClick Listeners */
    }

    /** Method that changes page to createOutfit page */
    public void createOutfit() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.createOutfit.class);
        startActivity(intent);
    }


}