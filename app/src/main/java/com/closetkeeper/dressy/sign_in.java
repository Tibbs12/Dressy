package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.closetkeeper.dressy.ui.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sign_in extends AppCompatActivity{

    /**Setting variables for each element used on XML*/

    private TextView existingAcct;
    private ImageButton signInBack;
    private Button loginBtn;
    private EditText loginEmailHint;
    private TextView loginError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        loginEmailHint = (EditText) findViewById(R.id.loginEmailHint);
        loginError = (TextView) findViewById(R.id.loginError);

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

        String email;
        email = loginEmailHint.getText().toString().trim();
        Pattern p = Pattern.compile("@");
        Pattern p2 = Pattern.compile(".");
        Matcher m = p.matcher(email);
        Matcher m2 = p2.matcher(email);

        if(m.find() && m2.find()) {

            /**NOTE: This is where the account class needs to check to see if the account is valid*/
            Intent intent = new Intent(this, com.closetkeeper.dressy.home.class);
            startActivity(intent);
        }
        else
            loginError.setVisibility(View.VISIBLE);
    }
}