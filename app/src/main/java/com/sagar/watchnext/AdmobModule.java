package com.sagar.watchnext;


import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import dagger.Module;
import dagger.Provides;

@Module
public class AdmobModule {

    @Provides
    AdRequest request() {
        return new AdRequest.Builder()
                .build();
    }

    @Provides
    @ApplicationScope
    InterstitialAd interstitialAd(Context context, AdRequest adRequest) {
        InterstitialAd ad = new InterstitialAd(context);
        ad.setAdUnitId(context.getResources().getString(R.string.admob_full_screen_ad));
        ad.loadAd(adRequest);
        return ad;
    }
}
