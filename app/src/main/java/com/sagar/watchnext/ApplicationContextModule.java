package com.sagar.watchnext;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 12:48
 */

@Module
public class ApplicationContextModule {
    private Context context;

    public ApplicationContextModule(Context context) {
        this.context = context;
    }

    @ApplicationScope
    @Provides
    public Context provideContext() {
        return this.context;
    }
}
