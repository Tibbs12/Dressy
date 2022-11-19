package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.closetkeeper.dressy.databinding.ActivityCalendarBinding;
import com.closetkeeper.dressy.databinding.ActivityCreateClosetBinding;

public class createCloset extends AppCompatActivity {

    ActivityCreateClosetBinding binding;
    private ImageButton createClosetBack;
    private AppCompatButton closetDoneBtn;

    private EditText closetInput; /** used to track the input of the user and transfer the data to next page*/

    private static String closetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateClosetBinding.inflate(getLayoutInflater());
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

        closetInput = (EditText) findViewById(R.id.closetInput);

        /** setting variables equal to the xml button and creating OnClick listeners */


        closetDoneBtn = (AppCompatButton) findViewById(R.id.closetDoneBtn);
        closetDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCloset();
            }
        });
    }

    /**Opens Home activity page*/
    public void openHome() {

        finish();
    }

    /**Opens selectOutfit activity page*/
    public void selectCloset() {
        closetName = closetInput.getText().toString().trim();

        Intent intent = new Intent(this, com.closetkeeper.dressy.selectCloset.class);

        /** This putExtra function passes the actual string to the new page */
        intent.putExtra(com.closetkeeper.dressy.selectCloset.CLOSETNAME, closetName);

        startActivity(intent);      //Take in user input from this XML sheet and pass it to the new page
    }

}