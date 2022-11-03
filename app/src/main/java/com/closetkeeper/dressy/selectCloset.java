package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class selectCloset extends AppCompatActivity {

    private ImageButton closetBackBtn;           /**Instantiating the ImageButtons*/
    private ImageButton closetAddOutfits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_closet);

        /** setting variables equal to the xml button and creating OnClick listeners */

        closetBackBtn = (ImageButton) findViewById(R.id.closetBackBtn);
        closetBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCloset();
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

    /** Method that changes page to createCloset page */
    public void createCloset() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.createCloset.class);
        startActivity(intent);
    }
}