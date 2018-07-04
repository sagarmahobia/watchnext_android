package com.sagar.watchnext.screens;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 13:39
 */

@Module
public class MainActivityModule {
    private MainActivity activity;


    MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @MainActivityScope
    public MainActivity providesMainActivity() {
        return this.activity;
    }
}
