package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class sign_up extends AppCompatActivity {

    /**
     * Setting variables for each element used on XML
     */

    private TextView textView6;
    private ImageButton signUpBack;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

    /**
    * Onclick listeners for signup activity
    */

        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        signUpBack = (ImageButton) findViewById(R.id.signUpBack);
        signUpBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccess();
            }
        });

        textView6 = (TextView) findViewById(R.id.textView6);
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignIn();
            }
        });
    }

    /**
     * Start of methods used to get to different pages
     */

    /**Opens sign_in page*/
    public void openSignIn() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.sign_in.class);
        startActivity(intent);
    }

    /**Opens access page*/
    public void openAccess() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.access.class);
        startActivity(intent);
    }

    /**Opens Main activity page*/
    public void openMain() {

        /**NOTE: This is where the account class needs to check to see if the account is valid*/
        Intent intent = new Intent(this, com.closetkeeper.dressy.ui.MainActivity.class);
        startActivity(intent);
    }

}
