package com.closetkeeper.dressy;

import static com.closetkeeper.dressy.home.Closets;
import static com.closetkeeper.dressy.home.Items;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
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
import com.closetkeeper.dressy.dto.Item;

public class my_closets extends AppCompatActivity {

    ActivityMyClosetsBinding binding;
    public static final int RequestPermissionCode = 1;
    private GridLayout closetsGridLayout;

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

        //For loop to display Closets
        for (String string : Closets)
         {
             TextView map = new TextView(this);/** This code adds a button each time*/
             //map.setLayoutParams(closetsGridLayout.getLayoutParams());
             map.setText(string);
             closetsGridLayout.addView(map);
         }
        }


    /** methods for camera */
    private void EnableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(my_closets.this, Manifest.permission.CAMERA)) {
            Toast.makeText(my_closets.this, "CAMERA permission allows us to Access your camera", Toast.LENGTH_LONG).show();
        }
        else
        {
            ActivityCompat.requestPermissions(my_closets.this, new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);
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
                    Toast.makeText(my_closets.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(my_closets.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}