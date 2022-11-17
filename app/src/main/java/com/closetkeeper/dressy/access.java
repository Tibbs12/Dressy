package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Java class for Access and methods used on access activity_access.xml
 *
 *Created by Matt on 10/29/22
 */


public class access extends AppCompatActivity {

    private Button accessSignUpBtn;       /**Setting up the two buttons here*/
    private Button accessLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        /**Start of the OnClick listeners for each element*/
        accessSignUpBtn = (Button) findViewById(R.id.accessSignUpBtn);
        accessSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });

        accessLoginBtn = (Button) findViewById(R.id.accessLoginBtn);
        accessLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignIn();
            }
        });

    }
    /**Start of methods that Onclick listeners will call*/

    /**Opens Sign up page*/
    public void openSignUp() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.my_items.class);
        startActivity(intent);
    }

    /**Opens Login page*/
    public void openSignIn() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.sign_in.class);
        startActivity(intent);
    }
}