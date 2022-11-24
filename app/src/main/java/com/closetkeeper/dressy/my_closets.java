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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
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
    private Button addMyClosets;
    private ImageButton myClosetsDelete;
    private static String closetIndex; //used to pass index of outfit selected to edit page.
    AlertDialog.Builder builder;
    private SearchView myClosetsSearch;
    private ListView listview;
    ArrayAdapter<String> arrayAdapter;
    public static List<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyClosetsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavBar.setSelectedItemId(R.id.closetNavBtn);/** this sets the correct nav button each time we load this page */

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

        listview = findViewById(R.id.listview); //search bar stuff

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listview.setAdapter(arrayAdapter);
        listview.setVisibility(View.VISIBLE);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int i, long l){

                     new AlertDialog.Builder(my_closets.this)
                             .setTitle("Do you want to delete" + names.get(i) + "?")
                             .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                                 @Override
                                 public void onClick(DialogInterface dialogInterface, int index) {
                                     Closets.remove(i);
                                     names.remove(i);
                                     arrayAdapter.notifyDataSetChanged();
                                }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialogInterface, int i) {
                                     dialogInterface.dismiss();
                                 }
                             }).create().show();
                return true;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                String closetIndex;
                closetIndex = Integer.toString(position);
                openCanvas(closetIndex);            /** need to pass outfit to canvas page */

            }
        });


        myClosetsSearch = (SearchView) findViewById(R.id.myClosetsSearch);          //search bar
        myClosetsSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query){
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

        /*myClosetsSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listview.setVisibility(View.VISIBLE);
            }
        });*/
       /* myClosetsSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClosetsSearch.onActionViewExpanded();
                listview.setVisibility(View.VISIBLE);
            }
        }); */


        names.removeAll(names);
        //For loop to display Outfits
        for (Closet string : Closets)
        {
            names.add(string.getName());
        }



        addMyClosets = findViewById(R.id.addMyClosets);
        addMyClosets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openCloset();}
        });

        /**myClosetsDelete = (ImageButton) findViewById(R.id.myClosetsDelete);
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
                        //updateView(closetsGridLayout);
                    }
                }).show();
            }
        });*/
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


}