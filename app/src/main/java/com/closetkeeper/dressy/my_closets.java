package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import com.closetkeeper.dressy.databinding.ActivityMyClosetsBinding;

public class my_closets extends AppCompatActivity {

    ActivityMyClosetsBinding binding;

    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyClosetsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavBar.setSelectedItemId(R.id.closetNavBtn);  /** this sets the correct nav button each time we load this page */

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
                    /**Intent add = new Intent(this, com.closetkeeper.dressy.???.class);
                     startActivity(add); */
                    EnableRuntimePermission();
                    Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera, 7); /** this is connected to "OnActivityResult" Method */
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