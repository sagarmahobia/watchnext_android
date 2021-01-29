package com.sagar.watchnext;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.sagar.watchnext.utils.SharedPreferenceService;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 12:45
 */


public class WatchNextApplication extends Application implements HasAndroidInjector {


    @Inject
    DispatchingAndroidInjector<Object> activityDispatchingAndroidInjector;


    @Inject
    SharedPreferenceService preferenceService;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerWatchNextApplicationComponent
                .factory()
                .create(this)
                .inject(this);

        List<String> testDeviceIds;
        testDeviceIds = Arrays.asList("67E7DD5073766409992B5DE33B951204", "4D06A259E0AAA30DEA1436D28C159197");
        RequestConfiguration configuration =
                new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
        MobileAds.setRequestConfiguration(configuration);

        preferenceService.incrementLaunchCount();
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return activityDispatchingAndroidInjector;
    }
}
