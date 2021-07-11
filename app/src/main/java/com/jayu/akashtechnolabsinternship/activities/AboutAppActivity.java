package com.jayu.akashtechnolabsinternship.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.jayu.akashtechnolabsinternship.R;

import java.util.Objects;

public class AboutAppActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imgGitHub,imgLinkedIn,imgGmail,imgTwitter,imgInstagram,imgPlayStore;
    TextView aboutAppDescription,aboutMeDescription;

    final String appDescription = "I have created this application as my project in my 15 days Summer Internship at Akash Technolabs, Ahmedabad. This app contains 5 mini projects named BMI Calculator, Prime Number Checker, Number Converter, Credit Card Checker, Temperature Converter. BMI Calculator (Body mass index) is a measure of body fat based on height and weight that applies to adult men and women. Prime Number Checker contains single digit check for prime or you can fetch prime numbers between two numbers. Number Converter contains converter of Binary, Octal, Decimal, Hexadecimal to each other converter. Credit Card Checker works on LUHN’s algorithm which is used to check Credit card numbers. Temperature Converter contains converter of Celsius, Fahrenheit, and kelvin to each other converter.";
    final String meDescription = "My name is Jay Parmar. I'm a CE student. I am developer of Python, Android, Flutter, C language, C#, JAVA, etc. If you wanted any kind of help in any of given languages you can directly contact with me using Telegram. The link for same is given below. I am happy to help you. My all projects are open source. you can contact me or download any of my open source project source code from below mentioned button.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        toolbar = findViewById(R.id.toolbar);
        imgGitHub = findViewById(R.id.imgGitHub);
        imgLinkedIn = findViewById(R.id.imgLinkedIn);
        imgGmail = findViewById(R.id.imgGmail);
        imgTwitter = findViewById(R.id.imgTwitter);
        imgInstagram = findViewById(R.id.imgInstagram);
        imgPlayStore = findViewById(R.id.imgPlayStore);
        aboutAppDescription = findViewById(R.id.aboutAppDescription);
        aboutMeDescription = findViewById(R.id.aboutMeDescription);
        setUpToolbar();

        aboutMeDescription.setText(meDescription);
        aboutAppDescription.setText(appDescription);

        imgGitHub.setOnClickListener(view -> {
            String url = "http://github.com/DeveloperJayu";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        imgLinkedIn.setOnClickListener(view -> {
            String url = "https://www.linkedin.com/in/developerjayu/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        imgGmail.setOnClickListener(view -> {
            String url = "mailto:developerjayu@gmail.com";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        imgTwitter.setOnClickListener(view -> {
            String url = "https://twitter.com/ParmarJay38";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        imgInstagram.setOnClickListener(view -> {
            String url = "https://www.instagram.com/km_60_baki_majama";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        imgPlayStore.setOnClickListener(view -> {
            String url = "https://play.google.com/store/apps/developer?id=Developer%20Jayu";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("About App");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}