package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class home extends AppCompatActivity {

    private ImageButton newclosetbtn;           /**Instantiating the two ImageButtons*/
    private ImageButton newoutfitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

        /** End of OnClick Listeners */
    }

    /** Start of methods createCloset and createOutfit */

    public void createCloset() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.createCloset.class);
        startActivity(intent);
    }

    public void createOutfit() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.createOutfit.class);
        startActivity(intent);
    }
}