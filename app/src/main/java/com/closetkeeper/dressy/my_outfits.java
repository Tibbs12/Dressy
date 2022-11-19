package com.closetkeeper.dressy;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.closetkeeper.dressy.databinding.ActivityMyOutfitsBinding;
import com.closetkeeper.dressy.dto.Item;
import com.closetkeeper.dressy.dto.Outfit;

import java.util.ArrayList;
import java.util.List;

public class my_outfits extends AppCompatActivity {

    ActivityMyOutfitsBinding binding;
    private GridLayout outfitsGridLayout;
    private ImageButton addOutfit;
    private ImageButton deleteOutfit;
    AlertDialog.Builder builder;

    public static List<TextView> newViewList = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyOutfitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavBar.setSelectedItemId(R.id.outfitsNavBtn);  /** this sets the correct nav button each time we load this page */

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
                    /** Intent search = new Intent(this, com.closetkeeper.dressy.my_closets.class);
                     startActivity(search); */
                    break;
                case R.id.outfitsNavBtn:

                    break;
            }


            return true;
        });

        outfitsGridLayout = findViewById(R.id.outfitsGridLayout);

        newViewList.removeAll(newViewList);
        //For loop to display Outfits
        for (Outfit string : Outfits)
         {
             TextView map = new TextView(this);/** This code adds a button each time*/
             map.setText(string.getName().toString());
             map.setClickable(true);
             map.setPadding(18, 18, 18, 18);
             map.setId(Outfits.indexOf(string));
             map.setSelected(false);
             createOnClick(map);
             createOnLongClick(map);
             outfitsGridLayout.addView(map);
             newViewList.add(map);
         }



        addOutfit = findViewById(R.id.addOutfit);
        addOutfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOutfit();
            }
        });

        deleteOutfit = (ImageButton) findViewById(R.id.deleteOutfit);
        builder = new AlertDialog.Builder(this);
        deleteOutfit.setOnClickListener(new View.OnClickListener() {
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
                        for (TextView view : newViewList ) {
                            if (view.isSelected()){
                                Outfits.remove(view.getId() - x);
                                x++;
                            }
                        }
                        updateView(outfitsGridLayout);
                    }
                }).show();
            }
        });
    }



    public void openCanvas() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.outfit_canvas.class);
        startActivity(intent);
    }


    public void openOutfit() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.createOutfit.class);
        startActivity(intent);
    }


    /** this method creates an onclick listener for every button that calls this method. */
    public void createOnClick(TextView map){
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCanvas();                   /** need to pass outfit to canvas page */
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
        newViewList.removeAll(newViewList);/**removing all views from grid and then running loop again */
        for (Outfit image : Outfits) {
            TextView map = new TextView(this);/** This code adds a button each time*/
            map.setText(image.getName().toString());
            map.setClickable(true);
            map.setPadding(18, 18, 18, 18);
            map.setId(Outfits.indexOf(image));
            map.setSelected(false);
            createOnClick(map);
            createOnLongClick(map);
            outfitsGridLayout.addView(map);
            newViewList.add(map);
        }
    }
}