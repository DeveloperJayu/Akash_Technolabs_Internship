package com.jayu.akashtechnolabsinternship.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.jayu.akashtechnolabsinternship.R;
import com.jayu.akashtechnolabsinternship.fragments.BMICalculatorFragment;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setUpToolbar();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,new BMICalculatorFragment())
                .commit();
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
    }
}