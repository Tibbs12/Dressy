package com.closetkeeper.dressy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.closetkeeper.dressy.ui.MainActivity;

public class sign_in extends AppCompatActivity{
    private TextView existingAcct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        existingAcct = (TextView) findViewById(R.id.existingAcct);
        existingAcct.setOnClickListener(new View.OnClickListener() {
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
}