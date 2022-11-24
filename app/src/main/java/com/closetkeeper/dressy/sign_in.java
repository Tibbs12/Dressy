package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.closetkeeper.dressy.dao.IAccountData;
import com.closetkeeper.dressy.ui.MainActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sign_in extends AppCompatActivity{

    /**Setting variables for each element used on XML*/

    private TextView existingAcct;
    private ImageButton signInBack;
    private Button loginBtn;
    private EditText loginEmailHint;
    private TextView loginError;
    public static Boolean hasAccountFile;
    private List<String> internalUserData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //Retrieves the status of internal user data files (if they already exist or not) from the Access.java class
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            hasAccountFile = extras.getBoolean("userDataStatus");
        }
        //Read internal user data from device, if exists
        if(hasAccountFile){
            //Toast.makeText(this, "Device HAS account data files", Toast.LENGTH_SHORT).show();
            try {
                readInternalUserData();
            } catch (IOException e) {
                System.out.println("Couldn't read internal file on device");
                e.printStackTrace();
            }
        }


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
        intent.putExtra("userDataStatus", hasAccountFile);
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

        //Test internal Account data
        if(hasAccountFile){
            Toast.makeText(this, "Account ID: " + internalUserData.get(0), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Account Email: " + internalUserData.get(1), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Account Password: " + internalUserData.get(2), Toast.LENGTH_SHORT).show();
        }

        //if(m.find() && m2.find()) {           Commented out for now to allow easy access

            /**NOTE: This is where the account class needs to check to see if the account is valid*/
            Intent intent = new Intent(this, com.closetkeeper.dressy.home.class);
            intent.putExtra("userDataStatus", hasAccountFile);
            startActivity(intent);
       // }
        //else
            //loginError.setVisibility(View.VISIBLE);
    }

    /**
     * Reads the specific file from within the device's internal storage. This file contains user data like Account ID, Email, and Password. Adds the data
     * to the List<String> variable called "internalUserData". Format of List is as follows:
     * Index 1 -> User ID
     * Index 2 -> User Email
     * Index 3 -> User Password
     * @throws IOException
     */
    private void readInternalUserData() throws IOException{

        FileInputStream fin = openFileInput(IAccountData.ACCOUNT_DATA_FILENAME);
        InputStreamReader isr = new InputStreamReader(fin);
        BufferedReader reader = new BufferedReader(isr);

        String line1 = reader.readLine();
        internalUserData.add(line1);
        String line2 = reader.readLine();
        internalUserData.add(line2);
        String line3 = reader.readLine();
        internalUserData.add(line3);

        fin.close();
        reader.close();
    }
}