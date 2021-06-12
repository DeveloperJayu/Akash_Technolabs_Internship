package com.jayu.akashtechnolabsinternship.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.navigation.NavigationView;
import com.jayu.akashtechnolabsinternship.R;
import com.jayu.akashtechnolabsinternship.fragments.BMICalculatorFragment;
import com.jayu.akashtechnolabsinternship.fragments.CreditCardCheckerFragment;
import com.jayu.akashtechnolabsinternship.fragments.HomeFragment;
import com.jayu.akashtechnolabsinternship.fragments.NumberConverterFragment;
import com.jayu.akashtechnolabsinternship.fragments.PrimeNumberCheckerFragment;
import com.jayu.akashtechnolabsinternship.fragments.TemperatureConverterFragment;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    AdView adView,mAdView;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        adView = new AdView(this);
        mAdView = findViewById(R.id.adView);
        setUpToolbar();

        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getResources().getString(R.string.bannerAds_final));
        MobileAds.initialize(this);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        InterstitialAd.load(this, getResources().getString(R.string.interstitialAds_final), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        mInterstitialAd = null;
                        Intent intent = new Intent(MainActivity.this,AboutAppActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }
                });
                super.onAdLoaded(interstitialAd);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
                super.onAdFailedToLoad(loadAdError);
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,new HomeFragment())
                .commit();

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
            else if (item.getItemId() == R.id.menuTemperatureConverter){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new TemperatureConverterFragment())
                        .addToBackStack("Temperature Converter")
                        .commit();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if (item.getItemId() == R.id.menuAboutApp){
                Intent intent = new Intent(MainActivity.this,AboutAppActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if (item.getItemId() == R.id.menuMyOtherApps){
                String url = "https://play.google.com/store/apps/developer?id=Developer%20Jayu";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.kebab_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.kebabAboutApp){
            if (mInterstitialAd != null){
                mInterstitialAd.show(MainActivity.this);
            }
            else {
                Intent intent = new Intent(MainActivity.this,AboutAppActivity.class);
                startActivity(intent);
            }
        }
        else if (item.getItemId() == R.id.kebabMyOtherApps){
            String url = "https://play.google.com/store/apps/developer?id=Developer%20Jayu";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}