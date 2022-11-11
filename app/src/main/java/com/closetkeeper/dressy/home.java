package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class home extends AppCompatActivity {

    private ImageButton newclosetbtn;           /**Instantiating the two ImageButtons*/
    private ImageButton newoutfitbtn;
    private TextView calendarClick;
    private LinearLayout MyClosets;
    private LinearLayout MyOutfits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MyClosets = (LinearLayout) findViewById(R.id.MyClosets);
        MyOutfits = (LinearLayout) findViewById(R.id.MyOutfits);

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

        /** End of OnClick Listeners */
    }

    /** Start of methods createCloset and createOutfit */

    public void createCloset() {
        ImageButton closet = new ImageButton(this);         /** This code adds a button each time*/
        closet.setLayoutParams(MyClosets.getLayoutParams());       /** we create a new closet */
        MyClosets.addView(closet);
        Intent intent = new Intent(this, com.closetkeeper.dressy.createCloset.class);
        startActivity(intent);
    }

    public void createOutfit() {
        ImageButton outfit = new ImageButton(this);         /** This code adds a button each time*/
        outfit.setLayoutParams(MyOutfits.getLayoutParams());       /** we create a new outfit */
        MyOutfits.addView(outfit);
        Intent intent = new Intent(this, com.closetkeeper.dressy.createOutfit.class);
        startActivity(intent);
    }

    public void openCalendar() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.calendar.class);
        startActivity(intent);
    }
}