package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class createCloset extends AppCompatActivity {

    private ImageButton createClosetBack;
    private AppCompatButton closetDoneBtn;

    private EditText closetInput; /** used to track the input of the user and transfer the data to next page*/

    private static String closetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_closet);

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