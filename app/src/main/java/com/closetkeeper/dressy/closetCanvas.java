package com.closetkeeper.dressy;

import static com.closetkeeper.dressy.home.Closets;
import static com.closetkeeper.dressy.home.Outfits;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.closetkeeper.dressy.databinding.ActivityClosetCanvasBinding;
import com.closetkeeper.dressy.dto.Closet;
import com.closetkeeper.dressy.dto.Item;
import com.closetkeeper.dressy.dto.Outfit;

import java.util.ArrayList;
import java.util.List;

public class closetCanvas extends AppCompatActivity {

    ActivityClosetCanvasBinding binding;
    private TextView closetNameCanvas;
    private GridLayout closetGridLayout;
    private ImageButton deleteCloset;
    AlertDialog.Builder builder;

    public final static String INDEX = "0";
    private int index;
    public static List<TextView> newCanvasList = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClosetCanvasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        closetNameCanvas = findViewById(R.id.closetNameCanvas);
        closetGridLayout = findViewById(R.id.closetGridLayout);

        Intent i = getIntent();
        index = Integer.parseInt(i.getStringExtra(INDEX));

        Closet myCloset = Closets.get(index);

        closetNameCanvas.setText("Edit Closet:" + " " + "'" + myCloset.getName() + "'");



        //For loop to add view for every item in outfit
        newCanvasList.removeAll(newCanvasList);
        if (myCloset.getOutfitsLength() > 0)
        {
            for (Outfit image : myCloset.getOutfits()) {
                TextView map = new TextView(this);/** This code adds a button each time*/

                map.setText(image.getName());
                map.setClickable(true);
                map.setPadding(18, 18, 18, 18);
                map.setId(myCloset.getOutfits().indexOf(image));
                map.setSelected(false);
                closetGridLayout.addView(map);
                createOnLongClick(map);
                newCanvasList.add(map);
            }
        }




        //Navigation menu code
        binding.bottomNavBar.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.homeNavBtn:
                    Intent intent = new Intent(this, com.closetkeeper.dressy.home.class);
                    startActivity(intent);
                    break;
                case R.id.closetNavBtn:
                    Intent mycloset = new Intent(this, com.closetkeeper.dressy.my_closets.class);
                    startActivity(mycloset);
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



        deleteCloset = (ImageButton) findViewById(R.id.deleteCloset);
        builder = new AlertDialog.Builder(this);
        deleteCloset.setOnClickListener(new View.OnClickListener() {
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
                        for (TextView view : newCanvasList ) {
                            if (view.isSelected()){
                                myCloset.removeOutfit(view.getId() - x);   //remove outfit method
                                x++;
                            }
                        }
                        updateView(closetGridLayout, myCloset);
                    }
                }).show();
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

    public void updateView(GridLayout myLayout, Closet myCloset){
        myLayout.removeAllViews();
        newCanvasList.removeAll(newCanvasList);/**removing all views from grid and then running loop again */

        if (myCloset.getOutfitsLength() > 0)
        {
            for (Outfit image : myCloset.getOutfits()) {
                TextView map = new TextView(this);/** This code adds a button each time*/
                map.setText(image.getName());
                map.setClickable(true);
                map.setPadding(18, 18, 18, 18);
                map.setId(myCloset.getOutfits().indexOf(image));
                map.setSelected(false);
                closetGridLayout.addView(map);
                createOnLongClick(map);
                newCanvasList.add(map);
            }
        }
    }
}

