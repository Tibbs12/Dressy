package com.closetkeeper.dressy;

import static com.closetkeeper.dressy.home.Items;
import static com.closetkeeper.dressy.home.Outfits;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.closetkeeper.dressy.databinding.ActivityOutfitCanvasBinding;
import com.closetkeeper.dressy.dto.Item;
import com.closetkeeper.dressy.dto.Outfit;

import java.util.ArrayList;
import java.util.List;

public class outfit_canvas extends AppCompatActivity {

    ActivityOutfitCanvasBinding binding;
    private TextView outfitNameCanvas;
    private GridLayout canvasGridLayout;
    private ImageButton deleteCanvas;
    AlertDialog.Builder builder;

    public final static String INDEX = "0";
    private int index;
    public static List<ImageView> canvasList = new ArrayList<ImageView>();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutfitCanvasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        outfitNameCanvas = findViewById(R.id.outfitNameCanvas);
        canvasGridLayout = findViewById(R.id.canvasGridLayout);

        Intent i = getIntent();
        index = Integer.parseInt(i.getStringExtra(INDEX));

        Outfit myOutfit = Outfits.get(index);

        outfitNameCanvas.setText(myOutfit.getName());



        //For loop to add view for every item in outfit
        canvasList.removeAll(canvasList);
        if (myOutfit.getItemsLength() > 0)
        {
            for (Item image : myOutfit.getItems()) {
                ImageView map = new ImageView(this);/** This code adds a button each time*/

                map.setImageBitmap(image.getImage());
                map.setClickable(true);
                map.setPadding(18, 18, 18, 18);
                map.setId(myOutfit.getItems().indexOf(image));
                map.setSelected(false);
                canvasGridLayout.addView(map);
                createOnLongClick(map);
                canvasList.add(map);
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


        deleteCanvas = (ImageButton) findViewById(R.id.deleteCanvas);
        builder = new AlertDialog.Builder(this);
        deleteCanvas.setOnClickListener(new View.OnClickListener() {
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
                        for (ImageView view : canvasList ) {
                            if (view.isSelected()){
                                myOutfit.removeItem(view.getId() - x);
                                x++;
                            }
                        }
                        updateView(canvasGridLayout, myOutfit);
                    }
                }).show();
            }
        });
    }

    /** this method creates an onlongclick listener for every button that calls this method. */
    public void createOnLongClick(ImageView map){
        map.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if (map.isSelected() != true)
                {
                    map.setAlpha(70);
                    map.setSelected(true);

                }
                else { //isSelected() is true
                    map.setAlpha(1000);
                    map.setSelected(false);
                    //map.setBackgroundColor(getResources().getColor(R.color.purple));
                }
                return true;
            }
        });
    }

    public void updateView(GridLayout myLayout, Outfit myOutfit){
        myLayout.removeAllViews();
        canvasList.removeAll(canvasList);/**removing all views from grid and then running loop again */

        if (myOutfit.getItemsLength() > 0)
        {
            for (Item image : myOutfit.getItems()) {
                ImageView map = new ImageView(this);/** This code adds a button each time*/
                map.setImageBitmap(image.getImage());
                map.setClickable(true);
                map.setSelected(false);
                map.setId(myOutfit.getItems().indexOf(image));
                map.setPadding(18, 18, 18, 18);
                canvasList.add(map);
                canvasGridLayout.addView(map);
                createOnLongClick(map);
            }
        }
    }
}