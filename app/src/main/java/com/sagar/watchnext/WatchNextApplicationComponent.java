package com.sagar.watchnext;


import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 12:46
 */

@ApplicationScope
@Component(modules = {ApplicationContextModule.class,
        NetworkModule.class,
        ActivityProvider.class})
public interface WatchNextApplicationComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        WatchNextApplicationComponent build();
    }

    void inject(WatchNextApplication watchNextApplication);

}
