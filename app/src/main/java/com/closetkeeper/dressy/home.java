package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.closetkeeper.dressy.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class home extends AppCompatActivity {

    ActivityHomeBinding binding;


    private ImageButton newclosetbtn;           /**Instantiating the two ImageButtons*/
    private ImageButton newoutfitbtn;
    private TextView calendarClick;
    private LinearLayout MyClosets;
    private LinearLayout MyOutfits;

    /** list for each item, outfit, and closet */
    public static List<Bitmap> Items = new ArrayList<Bitmap>();  /** Items will have a bitmap and String "tag" */
    public static Bitmap startingList[] = {};

    public static List<String> Outfits = new ArrayList<String>();
    public static String startOutfit[] = {};
                                                        /** Both of these list need to be changed to Objects */
    public static List<String> Closets = new ArrayList<String>();
    public static String startClosets[] = {};

    public static ArrayAdapter adapter;

    /** Used for permissions to access camera*/
    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //updateUI();


        MyClosets = (LinearLayout) findViewById(R.id.MyClosets);
        MyOutfits = (LinearLayout) findViewById(R.id.MyOutfits);

        //For loop to display Outfits
        for (String string : Outfits)
        {
            ImageButton map = new ImageButton(this);/** This code adds a button each time*/
            map.setLayoutParams(MyOutfits.getLayoutParams());
            //map.set(string); This needs to be figured out still
            MyOutfits.addView(map);
        }

        //For loop to display Closets
        for (String closet : Closets)
        {
            ImageButton map = new ImageButton(this);/** This code adds a button each time*/
            map.setLayoutParams(MyClosets.getLayoutParams());
            //map.set(closet); This needs to be figured out still
            MyClosets.addView(map);
        }


        /** setting variables equal to the xml button and creating OnClick listeners */

        newclosetbtn = (ImageButton) findViewById(R.id.newclosetbtn);
        newclosetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCloset();
            }
        });

        newoutfitbtn = (ImageButton) findViewById(R.id.newoutfitbtn);
        newoutfitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOutfit();
            }
        });

        calendarClick = (TextView) findViewById(R.id.calendarClick);
        calendarClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar();
            }
        });


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

        /** End of OnClick Listeners */
    }

    /** Start of methods createCloset and createOutfit */

    public void createCloset() {
      //  ImageButton closet = new ImageButton(this);         /** This code adds a button each time*/
      //  closet.setLayoutParams(MyClosets.getLayoutParams());       /** we create a new closet */
      //  MyClosets.addView(closet);
        Intent intent = new Intent(this, com.closetkeeper.dressy.createCloset.class);
        startActivity(intent);
    }

     public void createOutfit() {
        /*ImageButton outfit = new ImageButton(this);         /** This code adds a button each time*/
     /*   outfit.setLayoutParams(MyOutfits.getLayoutParams());       /** we create a new outfit */
      /*  MyOutfits.addView(outfit);
      Updated since last use. NOT NEEDED FOR NOW*/
        Intent intent = new Intent(this, com.closetkeeper.dressy.createOutfit.class);
        startActivity(intent);
    }

    public void openCalendar() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.calendar.class);
        startActivity(intent);
    }

    /** methods for camera */
    private void EnableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(home.this, Manifest.permission.CAMERA)) {
            Toast.makeText(home.this, "CAMERA permission allows us to Access your camera", Toast.LENGTH_LONG).show();
        }
        else
        {
            ActivityCompat.requestPermissions(home.this, new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);
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
            Items.add(bitmap);      //When items class is done we will use datatype Item instead of bitmap
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        super.onRequestPermissionsResult(requestCode, permissions, result);
        switch (requestCode) {
            case RequestPermissionCode:
                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(home.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(home.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

}