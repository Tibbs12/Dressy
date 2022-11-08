package com.closetkeeper.dressy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;

public class calendar extends AppCompatActivity {

    private CalendarView calendarView;  /** Instantiating calender and button */
    private ImageButton addOutfitBtn;
    private ImageButton calendarBack;
    private TextView MyDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        MyDate = (TextView) findViewById(R.id.MyDate);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

        /** OnClick listener for addOutfitButton */

        addOutfitBtn = (ImageButton) findViewById(R.id.addOutfitBtn);
        addOutfitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {               /** button on click listener for addoutfitbutton */
                outfitToCalender();
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 + "/" + i;
                MyDate.setText(date);               /** This method keeps track of the date */
            }
        });

        calendarBack = (ImageButton) findViewById(R.id.calendarBack);
        calendarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {           /** back button method*/
                finish();
            }
        });

    }
    /** saves date and outfit that is chosen by the user */
    public void outfitToCalender() {


    }
}