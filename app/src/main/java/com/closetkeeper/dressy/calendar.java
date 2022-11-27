package com.closetkeeper.dressy;

import static com.closetkeeper.dressy.home.Outfits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.closetkeeper.dressy.databinding.ActivityCalendarBinding;
import com.closetkeeper.dressy.dto.Outfit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class calendar extends AppCompatActivity {

    ActivityCalendarBinding binding;
    private CalendarView calendarView;  /** Instantiating calender and button */
    private ImageButton addOutfitBtn;
    private ImageButton calendarBack;
    private TextView MyDate;
    private ScrollView calendarList;
    private LinearLayout cake;
    private TextView outfitOnDay;
    public static List<TextView> views = new ArrayList<TextView>();
    public static List<Outfit> calendarOutfits = new ArrayList<Outfit>();
    public static List<String> Dates = new ArrayList<String>();
    public static String date = " ";

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

        calendarList = (ScrollView) findViewById(R.id.calendarList);

        cake = (LinearLayout) findViewById(R.id.cake);
        outfitOnDay = (TextView) findViewById(R.id.outfitOnDay);  //used to display an outfit that is on the current date.

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");       //Used to calculate current day
        date = sdf.format(cal.getTime());

        views.removeAll(views);
        for (Outfit outfit : Outfits) {
            TextView map = new TextView(this);
            //map.setId(Outfits.indexOf(outfit));
            map.setText(outfit.getName());
            cake.addView(map);
            createOnClick(map); //create a click function that selects the outfit
            views.add(map);

        }
        if (Dates.indexOf(date) != -1) {
            outfitOnDay.setText(calendarOutfits.get(Dates.indexOf(date)).getName());
        }
        else
        {
            outfitOnDay.setText("No outfit on this day");
        }

        /** OnClick listener for addOutfitButton */


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String day;

                date = (i1 + 1) + "/" + i2 + "/" + i;
                MyDate.setText(date); /** This method keeps track of the date */
                if (Dates.indexOf(date) != -1) {
                    outfitOnDay.setText(calendarOutfits.get(Dates.indexOf(date)).getName());
                }
                else
                {
                    outfitOnDay.setText("No outfit on this day");
                }


            }
        });

        outfitOnDay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (outfitOnDay.getText() != "No outfit on this day"){
                    totd();
                }

            }
        });



    }

    private void totd() {
        String index = Integer.toString(Dates.indexOf(date));
        Intent intent = new Intent(this, com.closetkeeper.dressy.ootd.class);
        intent.putExtra(ootd.OUTFIT, index);
        intent.putExtra(ootd.DATE, date);
        startActivity(intent);
    }

    /** saves date and outfit that is chosen by the user */
    public void outfitToCalender() {

        //Dates.add(calendarView.getDate());
        //calendarOutfits.add()

    }

    public void createOnClick(TextView map){
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (Dates.indexOf(date) == -1) {            //Meaning the date is not in our list yet


                    new AlertDialog.Builder(calendar.this)
                            .setTitle("Do you want to add " + map.getText() + " " + "to" + " " + date + "?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int index) {
                                    //Add the current outfit "map" to the date
                                    Dates.add(date);
                                    calendarOutfits.add(Outfits.get(views.indexOf(map)));
                                    outfitOnDay.setText(Outfits.get(views.indexOf(map)).getName());
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).create().show();
                }
            }
        });
    }
}