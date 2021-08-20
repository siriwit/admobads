package com.siriwit.admob.admobads;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.interstitial.InterstitialAd;

public class AdsAppCompatActivity extends AppCompatActivity implements InterstitialAdDelegate {
    protected InterstitialAd mInterstitialAd;
    protected OnDismissedAdDelegate onDismissedAdDelegate = () -> { };

    protected void loadBannerAds(AdView adView) {
        if (AdsUtils.shouldShowBanner) {
            AdsUtils.initBannerAd(adView);
        }
    }

    protected void loadInterstitialAd(String interstitialAdId) {
        AdsUtils.loadInterstititalAd(this, this, interstitialAdId);
    }

    protected void tryShowInterstitialAd(String interstitialAdId, OnDismissedAdDelegate onDismissedAdDelegate) {
        this.onDismissedAdDelegate = onDismissedAdDelegate;
        AdsUtils.showInterstitialAd(this, mInterstitialAd, this, interstitialAdId, onDismissedAdDelegate);
    }

    @Override
    public void onAdLoaded(InterstitialAd interstitialAd) {
        mInterstitialAd = interstitialAd;
    }

    @Override
    public void onAdDismissedFullScreenContent() {
        if (onDismissedAdDelegate != null) {
            onDismissedAdDelegate.execute();
        }
    }
}
