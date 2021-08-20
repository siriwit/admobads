package com.siriwit.admob.admobads;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Date;

public class AdsUtils {

    public static final long MINUTE = 60*1000;
    public static int adInterval = 3;
    private static Date lastShownAdsDateTime = new Date(new Date().getTime() - (60 * MINUTE));
    public static boolean shouldShowBanner = true;
    public static boolean shouldShowInterstitial = true;

    public static boolean shouldShowAds() {
        if (!shouldShowInterstitial) {
            return false;
        }

        Date now = new Date();
        if (now.after(lastShownAdsDateTime)) {
            lastShownAdsDateTime = new Date(new Date().getTime() + (adInterval * MINUTE));
            return true;
        }
        return false;
    }

    public static void showInterstitialAd(Activity activity,
                                          InterstitialAd interstitialAd,
                                          InterstitialAdDelegate delegate,
                                          String interstitialAdId,
                                          OnDismissedAdDelegate adsNotReadyCommand) {
        if(interstitialAd != null && shouldShowAds()) {
            interstitialAd.show(activity);
            loadInterstititalAd(activity, delegate, interstitialAdId);
        } else {
            adsNotReadyCommand.execute();
        }
    }

    public static void loadInterstititalAd(Context context, InterstitialAdDelegate delegate, String interstitialAdId){
        AdRequest adRequest = new AdRequest.Builder().build();
        com.google.android.gms.ads.interstitial.InterstitialAd.load(
                context,
                interstitialAdId,
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                        delegate.onAdLoaded(interstitialAd);
                        setupInterstitialListener(interstitialAd, delegate);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        delegate.onAdLoaded(null);
                    }
                });
    }

    private static void setupInterstitialListener(InterstitialAd interstitialAd, InterstitialAdDelegate delegate) {
        if (interstitialAd == null) { return; }

        interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
            @Override
            public void onAdDismissedFullScreenContent() {
                delegate.onAdDismissedFullScreenContent();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
            }

            @Override
            public void onAdShowedFullScreenContent() {
                delegate.onAdLoaded(null);
            }
        });
    }

    public static void initBannerAd(AdView mAdView) {
        if (shouldShowBanner) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }
    }
}