package com.sagar.watchnext;

import android.app.Application;

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


    @Override
    public void onCreate() {
        super.onCreate();

        DaggerWatchNextApplicationComponent
                .factory()
                .create(this)
                .inject(this);
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return activityDispatchingAndroidInjector;
    }
}
