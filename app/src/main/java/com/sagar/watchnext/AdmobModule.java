package com.sagar.watchnext;


import com.google.android.gms.ads.AdRequest;

import dagger.Module;
import dagger.Provides;

@Module
public class AdmobModule {


    @Provides
    AdRequest request() {
        return new AdRequest.Builder()
                .build();
    }

}
