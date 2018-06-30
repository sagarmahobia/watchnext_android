package com.sagar.watchnext;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 12:46
 */
@Component(modules = ApplicationContextModule.class)
public interface WatchNextApplicationComponent {
    void inject(WatchNextApplication watchNextApplication);
}
