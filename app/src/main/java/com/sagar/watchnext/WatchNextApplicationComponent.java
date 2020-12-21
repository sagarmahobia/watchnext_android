package com.sagar.watchnext;


import android.app.Application;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 12:46
 */

@ApplicationScope
@Component(modules = {ApplicationContextModule.class,
        NetworkModule.class,
        AdmobModule.class,
        AndroidSupportInjectionModule.class,
        ActivityProvider.class})
public interface WatchNextApplicationComponent {


    @Component.Factory
    interface Factory {

        WatchNextApplicationComponent create(@BindsInstance Application application);
    }
    void inject(WatchNextApplication application);

    AdRequest adRequest();

    InterstitialAd interstitialAd();
}
