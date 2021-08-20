package com.siriwit.admob.admobads;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.interstitial.InterstitialAd;

public class AdsFragment extends Fragment implements InterstitialAdDelegate {
    protected InterstitialAd mInterstitialAd;
    private OnDismissedAdDelegate onDismissedAdDelegate = () -> { };

    protected void loadBannerAds(AdView adView) {
        if (AdsUtils.shouldShowBanner) {
            AdsUtils.initBannerAd(adView);
        }
    }

    protected void loadInterstitialAd(String interstitialAdId) {
        AdsUtils.loadInterstititalAd(getActivity(), this, interstitialAdId);
    }

    protected void tryShowInterstitialAd(String interstitialAdId, OnDismissedAdDelegate onDismissedAdDelegate) {
        this.onDismissedAdDelegate = onDismissedAdDelegate;
        AdsUtils.showInterstitialAd(getActivity(), mInterstitialAd, this, interstitialAdId, onDismissedAdDelegate);
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
