package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.closetkeeper.dressy.ui.MainActivity;

public class sign_in extends AppCompatActivity{

    /**Setting variables for each element used on XML*/

    private TextView existingAcct;
    private ImageButton signInBack;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        /**Onclick listeners for sign_in activity*/

        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        signInBack = (ImageButton) findViewById(R.id.signInBack);
        signInBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccess();
            }
        });

        existingAcct = (TextView) findViewById(R.id.existingAcct);
        existingAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });
    }

    /**Opens signup page*/
    public void openSignUp() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.sign_up.class);
        startActivity(intent);
    }

    /**Opens access page*/
    public void openAccess() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.access.class);
        startActivity(intent);
    }

    /**Opens Home activity page*/
    public void openHome() {

        /**NOTE: This is where the account class needs to check to see if the account is valid*/
        Intent intent = new Intent(this, com.closetkeeper.dressy.home.class);
        startActivity(intent);
    }
}