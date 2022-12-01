package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.closetkeeper.dressy.databinding.ActivityCreateClosetBinding;
import com.closetkeeper.dressy.databinding.ActivityCreateOutfitBinding;

/**
 * Java class for createOutfit XMl page
 *
 *Created by Matthew Russo on 10/29/22
 */

public class createOutfit extends AppCompatActivity {

    ActivityCreateOutfitBinding binding;
    private ImageButton createOutfitBack;
    private AppCompatButton outfitDoneBtn;

    private EditText outfitInput; /** used to track the input of the user and transfer the data to next page*/

    private static String outfitName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateOutfitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                    break;
                case R.id.outfitsNavBtn:
                    Intent outfit = new Intent(this, com.closetkeeper.dressy.my_outfits.class);
                    startActivity(outfit);
                    break;
            }


            return true;
        });

        outfitInput = (EditText) findViewById(R.id.outfitInput);

        /** setting variables equal to the xml button and creating OnClick listeners */



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