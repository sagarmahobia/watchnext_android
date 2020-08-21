package com.sagar.watchnext.activities.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 13:39
 */

@Module
public class MainActivityModule {
    @Provides
    @MainActivityScope
    MainActivity providesMainActivity(MainActivity activity) {
        return activity;
    }
}
