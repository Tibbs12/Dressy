package com.closetkeeper.dressy;

import static com.closetkeeper.dressy.home.Items;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.closetkeeper.dressy.dto.Item;

import java.util.ArrayList;
import java.util.List;

public class my_items extends AppCompatActivity {

    private ImageButton addCamera;
    private GridLayout myItemsGrid;
    private ImageButton deleteMyItems;

    /**
     * Used for permissions to access camera
     */
    public static final int RequestPermissionCode = 1;

    public static List<ImageView> myList = new ArrayList<ImageView>();
//myList[0] = Items[0] ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);

        myItemsGrid = findViewById(R.id.myItemsGrid);

        //For loop to display items
        myList.removeAll(myList);
        for (Item image : Items) {
            ImageView map = new ImageView(this);/** This code adds a button each time*/

            map.setImageBitmap(image.getImage());
            map.setClickable(true);
            map.setPadding(18, 18, 18, 18);
            map.setId(Items.indexOf(image));
            map.setSelected(false);
            myItemsGrid.addView(map);
            createOnLongClick(map);
            myList.add(map);
        }


        addCamera = (ImageButton) findViewById(R.id.addCamera);
        addCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnableRuntimePermission();
                Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 7); /** this is connected to "OnActivityResult" Method */
            }
        });

        deleteMyItems = (ImageButton) findViewById(R.id.deleteMyItems);
        deleteMyItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = 0;
                for (ImageView view : myList ) {
                    if (view.isSelected()){
                        Items.remove(view.getId() - x);
                        x++;
                    }

                    }
                updateView(myItemsGrid);
                }
            });


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
        if (requestCode == 7 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Item myItem = new Item();
            myItem.setImage(bitmap);
            Items.add(myItem); //When items class is done we will use datatype Item instead of bitmap
            updateView(myItemsGrid); //calls method to update the view
        }
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

    public void updateView(GridLayout myLayout){
        myLayout.removeAllViews();
        myList.removeAll(myList);/**removing all views from grid and then running loop again */
        for (Item image : Items) {
            ImageView map = new ImageView(this);/** This code adds a button each time*/
            map.setImageBitmap(image.getImage());
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
                    map.setPressed(false);
                    //map.setBackgroundColor(getResources().getColor(R.color.purple));
                }
                return true;
            }
        });
    }
}