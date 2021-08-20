package com.siriwit.admob.admobads;

import com.google.android.gms.ads.interstitial.InterstitialAd;

public interface InterstitialAdDelegate {
    void onAdLoaded(InterstitialAd interstitialAd);
    void onAdDismissedFullScreenContent();
}
