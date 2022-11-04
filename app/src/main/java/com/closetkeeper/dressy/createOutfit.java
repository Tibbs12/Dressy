package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class createOutfit extends AppCompatActivity {

    private ImageButton createOutfitBack;
    private AppCompatButton outfitDoneBtn;

    private EditText outfitInput; /** used to track the input of the user and transfer the data to next page*/

    private static String outfitName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_outfit);

        outfitInput = (EditText) findViewById(R.id.outfitInput);

        /** setting variables equal to the xml button and creating OnClick listeners */

        createOutfitBack = (ImageButton) findViewById(R.id.createOutfitBack);
        createOutfitBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        outfitDoneBtn = (AppCompatButton) findViewById(R.id.outfitDoneBtn);
        outfitDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOutfit();
            }
        });
    }

    /**Opens Home activity page*/
    public void openHome() {

        finish();                   /** Using this finish() method allows us to open the parent
                                    * page, this makes the back button on a page more functionally correct */
    }

    /**Opens selectOutfit activity page*/
    public void selectOutfit() {

        outfitName = outfitInput.getText().toString().trim();
        Intent intent = new Intent(this, com.closetkeeper.dressy.selectOutfit.class);

        /** This putExtra function passes the actual string to the new page */
        intent.putExtra(com.closetkeeper.dressy.selectOutfit.OUTFITNAME, outfitName);

        startActivity(intent);      //Take in user input from this XML sheet and pass it to the new page
    }
}