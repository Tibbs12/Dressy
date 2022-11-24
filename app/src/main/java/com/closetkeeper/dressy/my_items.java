package com.closetkeeper.dressy;

import static com.closetkeeper.dressy.home.Items;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Toast;

import com.closetkeeper.dressy.databinding.ActivityMyItemsBinding;
import com.closetkeeper.dressy.dto.Item;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class my_items extends AppCompatActivity {

    ActivityMyItemsBinding binding;
    private ImageButton addCamera;
    private GridLayout myItemsGrid;
    private ImageButton deleteMyItems;
    private ImageButton addGallery;
    AlertDialog.Builder builder;
    private ListView itemsListView;
    public static List<Integer> itemImages = new ArrayList<Integer>();
    private SearchView itemsSearch;

    //test list
    public static List<String> itemNames = new ArrayList<String>();

    /**
     * Used for permissions to access camera
     */
    public static final int RequestPermissionCode = 1;

    public static List<ImageView> myList = new ArrayList<ImageView>();
//myList[0] = Items[0] ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavBar.setSelectedItemId(R.id.addNavBtn);  /** this sets the correct nav button each time we load this page */
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


        itemsListView = findViewById(R.id.itemsListView);
        ItemAdapter arrayAdapter = new ItemAdapter(this, R.layout.list_row, Items);
        arrayAdapter.setItems(Items);
        itemsListView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        itemsListView.setVisibility(View.VISIBLE);
        itemsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int i, long l){

                new AlertDialog.Builder(my_items.this)
                        .setTitle("Do you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int index) {
                                arrayAdapter.remove(Items.get(i));
                                myList.remove(i);
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

       /* itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                String closetIndex;
                closetIndex = Integer.toString(position);
                openCanvas(closetIndex);            /** need to pass outfit to canvas page */

         //   }
       // });

        itemsSearch = (SearchView) findViewById(R.id.myItemsSearch);          //search bar
        itemsSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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


        //For loop to display items
        itemImages.removeAll(itemImages);
        itemNames.removeAll(itemNames);
        myList.removeAll(myList);
        int x = 1;
        for (Item image : Items) {
            ImageView map = new ImageView(this);/** This code adds a button each time*/

            //map.setImageBitmap(image.getImage());
            map.setClickable(true);
            map.setPadding(18, 18, 18, 18);
            map.setId(Items.indexOf(image));
            map.setSelected(false);
            //myItemsGrid.addView(map);
            createOnLongClick(map);
            myList.add(map);
            itemNames.add("Item" + x);
            x++;

        }

        EnableRuntimePermission();
        addCamera = (ImageButton) findViewById(R.id.addCamera);
        addCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 7); /** this is connected to "OnActivityResult" Method */
            }
        });

        addGallery = (ImageButton) findViewById(R.id.addGallery);
        addGallery.setSelected(false);
        addGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGallery.setSelected(true);
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 7);
            }
        });

/*        deleteMyItems = (ImageButton) findViewById(R.id.deleteMyItems);
        builder = new AlertDialog.Builder(this);
        deleteMyItems.setOnClickListener(new View.OnClickListener() {
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
                        for (ImageView view : myList ) {
                            if (view.isSelected()){
                                Items.remove(view.getId() - x);
                                x++;
                            }
                        }
                        updateView(itemsListView);
                    }
                }).show();
            }
        }); */
    }

    /**
     * methods for camera
     */
    private void EnableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(my_items.this, Manifest.permission.CAMERA)) {
            Toast.makeText(my_items.this, "CAMERA permission allows us to Access your camera", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(my_items.this, new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        runOnUiThread(new Runnable(){
            public void run(){
                if (requestCode == 7 && resultCode == RESULT_OK) {
                    if (addGallery.isSelected()){
                        final Uri imageUri = data.getData();
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(imageUri);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        final Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                        Bitmap newmap = Bitmap.createScaledBitmap(bitmap, 200, 200, false);
                        Item myItem = new Item();
                        myItem.setImage(newmap); //Just changed this
                        Items.add(myItem);
                        //updateView(itemsListView); //calls method to update the view
                        addGallery.setSelected(false);
                        //update(Items);
                    }
                    else
                    {
                        final Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        Bitmap newmap = Bitmap.createScaledBitmap(bitmap, 200, 200, false);
                        Item myItem = new Item();
                        myItem.setImage(newmap);
                        Items.add(myItem);

                        //updateView(itemsListView); //calls method to update the view

                    }
                    //arrayAdapter.notifyDataSetChanged();
                    update();

                }

            }
        });

    }

    private void update() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.my_items.class);
        startActivity(intent);
    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        super.onRequestPermissionsResult(requestCode, permissions, result);
        switch (requestCode) {
            case RequestPermissionCode:
                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(my_items.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void updateView(ListView itemsListView){
        myList.removeAll(myList);/**removing all views from grid and then running loop again */
        for (Item image : Items) {
            ImageView map = new ImageView(this);/** This code adds a button each time*/
            //map.setImageBitmap(image.getImage());
            map.setClickable(true);
            map.setSelected(false);
            map.setId(Items.indexOf(image));
            map.setPadding(18, 18, 18, 18);
            myList.add(map);
            myItemsGrid.addView(map);
            createOnLongClick(map);
        }
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

}