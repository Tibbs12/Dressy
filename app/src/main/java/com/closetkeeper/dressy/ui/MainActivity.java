package com.closetkeeper.dressy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import com.closetkeeper.dressy.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    /** Need static variables and methods here to keep list of items, closets, and outfits updated */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler;
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,com.closetkeeper.dressy.access.class);
                startActivity(intent);
                finish();
            }
        },3000);
        //We will first display the contents of the splash screen and then move to access page

        //Intent intent = new Intent(this, com.closetkeeper.dressy.access.class);
        //startActivity(intent);

    }
    /** this is where the static methods to add or remove items from the lists should be */




}