package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sign_up extends AppCompatActivity {

    /**
     * Setting variables for each element used on XML
     */

    private TextView textView6;
    private ImageButton signUpBack;
    private Button signUpBtn;
    private EditText emailHint;
    private TextView signUpError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailHint = (EditText) findViewById(R.id.emailHint);
        signUpError = (TextView) findViewById(R.id.signUpError);

    /**
    * Onclick listeners for signup activity
    */

        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
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

    /**Opens Home activity page*/
    public void openHome() {

        String email;
        email = emailHint.getText().toString().trim();
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
            signUpError.setVisibility(View.VISIBLE);
    }

}
