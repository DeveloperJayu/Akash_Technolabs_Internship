package com.jayu.akashtechnolabsinternship.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.jayu.akashtechnolabsinternship.R;
import com.jayu.akashtechnolabsinternship.fragments.BMICalculatorFragment;
import com.jayu.akashtechnolabsinternship.fragments.CreditCardCheckerFragment;
import com.jayu.akashtechnolabsinternship.fragments.NumberConverterFragment;
import com.jayu.akashtechnolabsinternship.fragments.PrimeNumberCheckerFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        setUpToolbar();
        setTitle("HOME");

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menuBMICalculator){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new BMICalculatorFragment())
                        .addToBackStack("BMI Calculator")
                        .commit();
                actionBarDrawerToggle.syncState();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if (item.getItemId() == R.id.menuPrimeNumber){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new PrimeNumberCheckerFragment())
                        .addToBackStack("Prime Number")
                        .commit();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if (item.getItemId() == R.id.menuNumberConverter){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new NumberConverterFragment())
                        .addToBackStack("Number Converter")
                        .commit();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if (item.getItemId() == R.id.menuCreditCardChecker){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new CreditCardCheckerFragment())
                        .addToBackStack("Credit Card Checker")
                        .commit();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            return true;
        });
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}