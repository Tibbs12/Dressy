package com.closetkeeper.dressy;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.closetkeeper.dressy.ui.MainActivity;

public class selectOutfit extends AppCompatActivity {

    private ImageButton outfitBackBtn;           /**Instantiating the ImageButtons*/
    private ImageButton addItemsBtn;
    private TextView outfitNameInput;
    private GridLayout gridLayout;

    /** Used for permissions to access camera*/
    public static final int RequestPermissionCode = 1;

    /** Need static variables to pass data using intent */
    public final static String OUTFITNAME = "OUTFITNAME";

    private String outfitName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_outfit);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        EnableRuntimePermission();

        /** Sets up textview on selectCloset page, sets passed data into the textview */
        outfitNameInput = (TextView) findViewById(R.id.outfitNameInput);
        Intent intent = getIntent();
        outfitName = intent.getStringExtra(OUTFITNAME);
        outfitNameInput.setText(outfitName);


        /** setting variables equal to the xml button and creating OnClick listeners */

        outfitBackBtn = (ImageButton) findViewById(R.id.outfitBackBtn);
        outfitBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addItemsBtn = (ImageButton) findViewById(R.id.addItemsBtn);
        addItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createItem(); /** This is where we need to call the item creation method */
            }
        });

        /** End of OnClick Listeners */
    }


    /** methods for camera */
    private void EnableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(selectOutfit.this, Manifest.permission.CAMERA)) {
            Toast.makeText(selectOutfit.this, "CAMERA permission allows us to Access your camera", Toast.LENGTH_LONG).show();
        }
        else
        {
            ActivityCompat.requestPermissions(selectOutfit.this, new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ImageView image = new ImageView(this);/** This code adds a button each time*/
            //image.setLayoutParams(gridLayout.getLayoutParams());
            gridLayout.addView(image);
            image.setImageBitmap(bitmap);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        super.onRequestPermissionsResult(requestCode, permissions, result);
        switch (requestCode) {
            case RequestPermissionCode:
                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(selectOutfit.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(selectOutfit.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    /** Empty method to prevent errors */
    public void createItem()
    {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 7); /** this is connected to "OnActivityResult" Method */
    }

}