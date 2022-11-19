package com.closetkeeper.dressy;

import static com.closetkeeper.dressy.home.Items;
import static com.closetkeeper.dressy.home.Outfits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.closetkeeper.dressy.databinding.ActivityMyOutfitsBinding;
import com.closetkeeper.dressy.dto.Item;
import com.closetkeeper.dressy.dto.Outfit;

public class my_outfits extends AppCompatActivity {

    ActivityMyOutfitsBinding binding;
    public static final int RequestPermissionCode = 1;
    private GridLayout outfitsGridLayout;

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

        //For loop to display Outfits
        for (Outfit string : Outfits)
         {
             TextView map = new TextView(this);/** This code adds a button each time*/
             //map.setLayoutParams(outfitsGridLayout.getLayoutParams());
             map.setText(string.getItems().toString());
             outfitsGridLayout.addView(map);
         }
    }


    /** methods for camera */
    private void EnableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(my_outfits.this, Manifest.permission.CAMERA)) {
            Toast.makeText(my_outfits.this, "CAMERA permission allows us to Access your camera", Toast.LENGTH_LONG).show();
        }
        else
        {
            ActivityCompat.requestPermissions(my_outfits.this, new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Item myItem = new Item();
            myItem.setImage(bitmap);
            Items.add(myItem);
            /** Instead of all below code, we will pass bitmap and tag string to method CreateItem(); */
            //ImageView image = new ImageView(this);/** This code adds a button each time*/
            //image.setLayoutParams(gridLayout.getLayoutParams());
            //gridLayout.addView(image);
            //image.setImageBitmap(bitmap);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        super.onRequestPermissionsResult(requestCode, permissions, result);
        switch (requestCode) {
            case RequestPermissionCode:
                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(my_outfits.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(my_outfits.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}