package com.closetkeeper.dressy;

import static com.closetkeeper.dressy.home.Items;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.closetkeeper.dressy.dto.Item;

public class my_items extends AppCompatActivity {

    private ImageButton addCamera;
    private GridLayout myItemsGrid;

    /**
     * Used for permissions to access camera
     */
    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);

        myItemsGrid = findViewById(R.id.myItemsGrid);

        //For loop to display items
        for (Item image : Items) {
            ImageView map = new ImageView(this);/** This code adds a button each time*/

            map.setImageBitmap(image.getImage());
            map.setClickable(true);
            map.setPadding(18, 18, 9, 0);
            myItemsGrid.addView(map);
            createOnLongClick(map);
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


    }

    /**Test method for onlongclick */
    public boolean onClick(View v) {
        v.setPadding(0,0,0,0);
        return true;
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
                } else {
                    Toast.makeText(my_items.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void updateView(GridLayout myLayout){
        myLayout.removeAllViews();                      /**removing all views from grid and then running loop again */
        for (Item image : Items) {
            ImageView map = new ImageView(this);/** This code adds a button each time*/
            map.setImageBitmap(image.getImage());
            map.setClickable(true);
            map.setPadding(18, 18, 9, 0);
            myItemsGrid.addView(map);
            createOnLongClick(map);
        }
    }

    public void createOnLongClick(ImageView map){
        map.setOnLongClickListener(new View.OnLongClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public boolean onLongClick(View v) {
                map.setSelected(true);
                map.setOutlineAmbientShadowColor(5);
                return true;
            }
        });
    }
}