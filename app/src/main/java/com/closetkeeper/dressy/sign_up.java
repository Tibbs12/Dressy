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
import java.io.IOException;
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
    private EditText pwdHint;
    public static Boolean hasAccountFile;
    Database serverData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Retrieves the status of internal user data files (if they already exist or not) from the Access.java class
        //Might not need to determine if internal user data exists or not for this class
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            hasAccountFile = extras.getBoolean("userDataStatus");
        }
        //TEST TO SEE IF DATA TRANSFERRED
        /*if(hasAccountFile){
            Toast.makeText(this, "Device HAS account data files", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Device DOESN'T HAVE account data files", Toast.LENGTH_SHORT).show();
        }*/

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
        serverData = new Database();
        Toast.makeText(this, "Account email to user ID 9: " + serverData.fetchEmail(9), Toast.LENGTH_SHORT).show();

        String email;
        String pass;
        pass = pwdHint.getText().toString().trim();
        email = emailHint.getText().toString().trim();
        Pattern p = Pattern.compile("@");
        Pattern p2 = Pattern.compile(".");
        Matcher m = p.matcher(email);
        Matcher m2 = p2.matcher(email);

        Account myAccount = new Account();
        
        myAccount.setEmail(email);
        myAccount.setPassword(pass);
        try {
            writeToInternalStorage("1000", myAccount.getEmail(), myAccount.getPassword());
            hasAccountFile = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**NOTE: This is where the account class needs to check to see if the account is valid*/
        Intent intent = new Intent(this, home.class);
        intent.putExtra("userDataStatus", hasAccountFile);
        startActivity(intent);

        //if(m.find() && m2.find()) {


        //}
        //else
            //signUpError.setVisibility(View.VISIBLE);
    }


    /**
     * Writes the user's account number, email, and password to their device's internal storage.
     * @param userID
     * @param userEmail
     * @param userPassword
     * @throws IOException
     */
    private void writeToInternalStorage(String userID, String userEmail, String userPassword) throws IOException {
        FileOutputStream fos = openFileOutput(IAccountData.ACCOUNT_DATA_FILENAME, Context.MODE_PRIVATE);
        String data = (userID + "\n" + userEmail + "\n" + userPassword);
        fos.write(data.getBytes());
        fos.close();
    }
}
