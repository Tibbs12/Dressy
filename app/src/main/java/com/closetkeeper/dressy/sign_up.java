package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.closetkeeper.dressy.dao.Database;
import com.closetkeeper.dressy.dao.IAccountData;
import com.closetkeeper.dressy.dto.Account;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Java class for sign_up xml page
 *
 * Created by Matthew Russo on 10/29/22
 * Backend updates and internal data storage by Tim on 11/12/22
 */

public class sign_up extends AppCompatActivity {

    /**
     * Setting variables for each element used on XML
     */

    private TextView textView6;
    private ImageButton signUpBack;
    private Button signUpBtn;
    private EditText emailHint;
    private TextView signUpError;
    private EditText pwdHint;
    Database serverData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailHint = (EditText) findViewById(R.id.emailHint);
        signUpError = (TextView) findViewById(R.id.signUpError);
        pwdHint = (EditText) findViewById(R.id.pwdHint);


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
        String pass;
        pass = pwdHint.getText().toString().trim();
        email = emailHint.getText().toString().trim();
        Pattern p = Pattern.compile("@");
        Pattern p2 = Pattern.compile(".");
        Matcher m = p.matcher(email);
        Matcher m2 = p2.matcher(email);

        Account MyAccount = new Account();
        
        MyAccount.setEmail(email);
        MyAccount.setPassword(pass);

        /**NOTE: This is where the account class needs to check to see if the account is valid*/
        Intent intent = new Intent(this, home.class);
        startActivity(intent);

        //if(m.find() && m2.find()) {


        //}
        //else
            //signUpError.setVisibility(View.VISIBLE);
    }


    //Still in work
    private void writeToInternalStorage() throws FileNotFoundException {
        FileOutputStream fos = openFileOutput(IAccountData.ACCOUNT_DATA_FILENAME, Context.MODE_PRIVATE);

    }


    //
    //TESTING - this functions can be deleted
    //
    private class DataConnection extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            //serverData = new Database();
            return null;
        }
    }

}
