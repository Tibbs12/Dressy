package com.closetkeeper.dressy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.closetkeeper.dressy.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void SignIn(View v) {
        setContentView(R.layout.activity_signin);
    }
    public void SignUp(View v) {
        setContentView(R.layout.activity_signup);
    }
}