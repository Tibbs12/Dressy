package com.closetkeeper.dressy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.closetkeeper.dressy.databinding.ActivityCalendarBinding;

import java.util.Date;

public class calendar extends AppCompatActivity {

    ActivityCalendarBinding binding;
    private CalendarView calendarView;  /** Instantiating calender and button */
    private ImageButton addOutfitBtn;
    private ImageButton calendarBack;
    private TextView MyDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Navigation menu code
        binding.bottomNavBar.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.homeNavBtn:
                    Intent intent = new Intent(this, com.closetkeeper.dressy.home.class);
                    startActivity(intent);
                    break;
                case R.id.closetNavBtn:
                    Intent closet = new Intent(this, com.closetkeeper.dressy.my_closets.class);
                    startActivity(closet);
                    break;
                case R.id.addNavBtn:
                    Intent items = new Intent(this, com.closetkeeper.dressy.my_items.class);
                    startActivity(items);
                    break;
                case R.id.searchNavBar:
                    break;
                case R.id.outfitsNavBtn:
                    Intent outfit = new Intent(this, com.closetkeeper.dressy.my_outfits.class);
                    startActivity(outfit);
                    break;
            }


            return true;
        });


        MyDate = (TextView) findViewById(R.id.MyDate);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

        /** OnClick listener for addOutfitButton */


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 + "/" + i;
                MyDate.setText(date);               /** This method keeps track of the date */
            }
        });



    }
    /** saves date and outfit that is chosen by the user */
    public void outfitToCalender() {


    }
}