package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class createOutfit extends AppCompatActivity {

    private ImageButton createOutfitBack;
    private AppCompatButton outfitDoneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_outfit);

        /** setting variables equal to the xml button and creating OnClick listeners */

        createOutfitBack = (ImageButton) findViewById(R.id.createOutfitBack);
        createOutfitBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        outfitDoneBtn = (AppCompatButton) findViewById(R.id.outfitDoneBtn);
        outfitDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOutfit();
            }
        });
    }

    /**Opens Home activity page*/
    public void openHome() {

        Intent intent = new Intent(this, com.closetkeeper.dressy.home.class);
        startActivity(intent);
    }

    /**Opens selectOutfit activity page*/
    public void selectOutfit() {

        Intent intent = new Intent(this, com.closetkeeper.dressy.selectOutfit.class);
        startActivity(intent);      //Take in user input from this XML sheet and pass it to the new page
    }
}