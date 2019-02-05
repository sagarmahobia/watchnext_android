package com.sagar.watchnext;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 12:48
 */

@Module
class ApplicationContextModule {

    @ApplicationScope
    @Provides
    Context provideContext(Application context) {
        return context;
    }
}
