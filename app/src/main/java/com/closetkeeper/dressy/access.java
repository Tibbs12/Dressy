package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.closetkeeper.dressy.dao.IAccountData;

import java.io.File;


/**
 * Java class for Access and methods used on access activity_access.xml
 *
 * Created by Tim and Matthew Russo on 10/29/22
 */


public class access extends AppCompatActivity {

    private Button accessSignUpBtn;       /**Setting up the two buttons here*/
    private Button accessLoginBtn;
    public Boolean hasAccountFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        //Check for account data file within the device's internal storage
        if(checkAccountDataFiles()){
            hasAccountFile = true;
            Toast.makeText(this, "Device HAS account data files", Toast.LENGTH_SHORT).show();
        }
        else{
            hasAccountFile = false;
            Toast.makeText(this, "Device HAS account data files", Toast.LENGTH_SHORT).show();
        }

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
        Intent intent = new Intent(this, com.closetkeeper.dressy.sign_up.class);
        startActivity(intent);
    }

    /**Opens Login page*/
    public void openSignIn() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.sign_in.class);
        startActivity(intent);
    }

    /**
     * Determines whether the user's device contains the appropriate file that holds important information like account ID, username, and password.
     * @return Boolean value on the existence of userData file.
     */
    private Boolean checkAccountDataFiles(){
        File userInfoFile = getBaseContext().getFileStreamPath(IAccountData.ACCOUNT_DATA_FILENAME);
        return userInfoFile.exists();
    }
}