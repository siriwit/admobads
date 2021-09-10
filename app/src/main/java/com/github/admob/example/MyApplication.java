package com.github.admob.example;

import android.app.Application;
import android.content.Context;

import com.github.siriwit.admobads.AppOpenManager;
import com.google.android.gms.ads.MobileAds;

public class MyApplication extends Application {

    private static AppOpenManager appOpenManager;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        MobileAds.initialize(this, initializationStatus -> { });

        String appOpenAdId = context.getResources().getString(R.string.sample_appopen);
        appOpenManager = new AppOpenManager(this, appOpenAdId);
    }
}
