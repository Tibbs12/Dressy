package com.closetkeeper.dressy;

import static com.closetkeeper.dressy.home.Closets;
import static com.closetkeeper.dressy.home.Items;
import static com.closetkeeper.dressy.home.Outfits;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.closetkeeper.dressy.databinding.ActivityMyClosetsBinding;
import com.closetkeeper.dressy.dto.Closet;
import com.closetkeeper.dressy.dto.Item;
import com.closetkeeper.dressy.dto.Outfit;

import java.util.ArrayList;
import java.util.List;

public class my_closets extends AppCompatActivity {

    ActivityMyClosetsBinding binding;
    private GridLayout closetsGridLayout;
    private ImageButton myClosetsAdd;
    private ImageButton myClosetsDelete;
    private static String closetIndex; //used to pass index of outfit selected to edit page.
    AlertDialog.Builder builder;

    public static List<TextView> closetViewList = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyClosetsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavBar.setSelectedItemId(R.id.closetNavBtn);  /** this sets the correct nav button each time we load this page */

        //Navigation menu code
        binding.bottomNavBar.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.homeNavBtn:
                    Intent intent = new Intent(this, com.closetkeeper.dressy.home.class);
                    startActivity(intent);
                    break;
                case R.id.closetNavBtn:
                    break;
                case R.id.addNavBtn:
                    Intent items = new Intent(this, com.closetkeeper.dressy.my_items.class);
                    startActivity(items);
                    break;
                case R.id.searchNavBar:
                    /** Intent search = new Intent(this, com.closetkeeper.dressy.my_closets.class);
                     startActivity(search); */
                    break;
                case R.id.outfitsNavBtn:
                    Intent outfit = new Intent(this, com.closetkeeper.dressy.my_outfits.class);
                    startActivity(outfit);

                    break;
            }


            return true;
        });
        closetsGridLayout = findViewById(R.id.closetsGridLayout);

        closetViewList.removeAll(closetViewList);
        //For loop to display Outfits
        for (Closet string : Closets)
        {
            TextView map = new TextView(this);/** This code adds a button each time*/
            map.setText(string.getName().toString());
            map.setClickable(true);
            map.setPadding(18, 18, 18, 18);
            map.setId(Closets.indexOf(string));
            map.setSelected(false);
            createOnClick(map);
            createOnLongClick(map);
            closetsGridLayout.addView(map);
            closetViewList.add(map);
        }



        myClosetsAdd = findViewById(R.id.myClosetsAdd);
        myClosetsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCloset();
            }
        });

        myClosetsDelete = (ImageButton) findViewById(R.id.myClosetsDelete);
        builder = new AlertDialog.Builder(this);
        myClosetsDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Are you sure you want to delete this item(s)?");
                builder.setCancelable(true);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int x = 0;
                        for (TextView view : closetViewList ) {
                            if (view.isSelected()){
                                Closets.remove(view.getId() - x);
                                x++;
                            }
                        }
                        updateView(closetsGridLayout);
                    }
                }).show();
            }
        });
    }

    public void openCanvas(String closetIndex) {
        Intent intent = new Intent(this, com.closetkeeper.dressy.closetCanvas.class);

        /** This putExtra function passes the actual string to the new page */
        intent.putExtra(outfit_canvas.INDEX, closetIndex);


        startActivity(intent);      //Take in user input from this XML sheet and pass it to the new page
    }


    public void openCloset() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.createCloset.class);
        startActivity(intent);
    }


    /** this method creates an onclick listener for every button that calls this method. */
    public void createOnClick(TextView map){
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String closetIndex;
                closetIndex = Integer.toString(v.getId());
                openCanvas(closetIndex);            /** need to pass outfit to canvas page */
            }
        });
    }


    /** this method creates an onlongclick listener for every button that calls this method. */
    public void createOnLongClick(TextView map){
        map.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if (map.isSelected() != true)
                {
                    map.setAllCaps(true);
                    map.setSelected(true);

                }
                else { //isSelected() is true
                    map.setAllCaps(false);
                    map.setSelected(false);
                    //map.setBackgroundColor(getResources().getColor(R.color.purple));
                }
                return true;
            }
        });
    }

    public void updateView(GridLayout myLayout){
        myLayout.removeAllViews();
        closetViewList.removeAll(closetViewList);/**removing all views from grid and then running loop again */
        for (Closet image : Closets) {
            TextView map = new TextView(this);/** This code adds a button each time*/
            map.setText(image.getName().toString());
            map.setClickable(true);
            map.setPadding(18, 18, 18, 18);
            map.setId(Closets.indexOf(image));
            map.setSelected(false);
            createOnClick(map);
            createOnLongClick(map);
            closetsGridLayout.addView(map);
            closetViewList.add(map);
        }
    }


}