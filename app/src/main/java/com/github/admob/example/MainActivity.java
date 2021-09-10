package com.github.admob.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.github.siriwit.admobads.AdsAppCompatActivity;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AdsAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView adView = findViewById(R.id.adView);
        loadBannerAds(adView);

        String interstitialAdId = getResources().getString(R.string.sample_interstitial_id);
        loadInterstitialAd(interstitialAdId);

        Button showInterStitial = findViewById(R.id.showInterstitial);
        showInterStitial.setOnClickListener(v -> {
            tryShowInterstitialAd(interstitialAdId, () -> {
                Toast.makeText(this, "Ad Interstitial Dismissed.", Toast.LENGTH_LONG).show();
            });
        });
    }
}