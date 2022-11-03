package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class selectOutfit extends AppCompatActivity {

    private ImageButton outfitBackBtn;           /**Instantiating the ImageButtons*/
    private ImageButton addItemsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_outfit);

        /** setting variables equal to the xml button and creating OnClick listeners */

        outfitBackBtn = (ImageButton) findViewById(R.id.outfitBackBtn);
        outfitBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addItemsBtn = (ImageButton) findViewById(R.id.addItemsBtn);
        addItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createItem(); /** This is where we need to call the item creation method */
            }
        });

        /** End of OnClick Listeners */
    }

    /** Empty method to prevent errors */
    public void createItem() {

    }

}