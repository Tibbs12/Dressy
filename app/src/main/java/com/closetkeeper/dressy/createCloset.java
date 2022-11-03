package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class createCloset extends AppCompatActivity {

    private ImageButton createClosetBack;
    private AppCompatButton closetDoneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_closet);

        /** setting variables equal to the xml button and creating OnClick listeners */

        createClosetBack = (ImageButton) findViewById(R.id.createClosetBack);
        createClosetBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openHome();
            }
        });

        closetDoneBtn = (AppCompatButton) findViewById(R.id.closetDoneBtn);
        closetDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCloset();
            }
        });
    }

    /**Opens Home activity page*/
    public void openHome() {

        Intent intent = new Intent(this, com.closetkeeper.dressy.home.class);
        startActivity(intent);
    }

    /**Opens selectOutfit activity page*/
    public void selectCloset() {

        Intent intent = new Intent(this, com.closetkeeper.dressy.selectOutfit.class);
        startActivity(intent);      //Take in user input from this XML sheet and pass it to the new page
    }
}