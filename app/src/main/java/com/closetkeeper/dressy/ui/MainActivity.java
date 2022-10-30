package com.closetkeeper.dressy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.closetkeeper.dressy.R;

public class MainActivity extends AppCompatActivity {
    private Button signIn;
    private Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignIn();
            }
        });

        signUp = (Button) findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });
    }

    public void openSignUp() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.sign_up.class);
        startActivity(intent);
    }

    public void openSignIn() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.sign_in.class);
        startActivity(intent);
    }


}