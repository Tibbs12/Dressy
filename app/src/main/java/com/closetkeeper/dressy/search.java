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

import com.closetkeeper.dressy.databinding.ActivitySearchBinding;
import com.closetkeeper.dressy.dto.Closet;
import com.closetkeeper.dressy.dto.Item;
import com.closetkeeper.dressy.dto.Outfit;

import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity {

    ActivitySearchBinding binding;
    private SearchView mySearch;
    private ListView searchList;
    ArrayAdapter<String> arrayAdapter;
    public static List<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Have the navigation bar show 'Search' as the selected button
        binding.bottomNavBar.setSelectedItemId(R.id.searchNavBar);

        // Implement the navigation bar
        binding.bottomNavBar.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.homeNavBtn:
                    Intent intent = new Intent(this, home.class);
                    startActivity(intent);
                    break;
                case R.id.closetNavBtn:
                    Intent closet = new Intent(this, my_closets.class);
                    startActivity(closet);
                    break;
                case R.id.addNavBtn:
                    Intent items = new Intent(this, my_items.class);
                    startActivity(items);
                    break;
                case R.id.searchNavBar:
                    break;
                case R.id.outfitsNavBtn:
                    Intent outfit = new Intent(this, my_outfits.class);
                    startActivity(outfit);

                    break;
            }


            return true;
        });

        // Link to searchList in activity_search
        searchList = findViewById(R.id.searchList);

        // Initialize the Adapter
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        searchList.setAdapter(arrayAdapter);

        // Link to mySearch in activity_search
        mySearch = (SearchView) findViewById(R.id.mySearch);

        // Adds text to the bar when clicked, for UI purposes
        mySearch.setQueryHint("Search your wardrobe...");

        // Create a listener to detect when the user starts typing in the Search Bar
        mySearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Starts to filter the results based on each character the user inputs
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });


    }}