package com.sagar.watchnext;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 12:45
 */
@ApplicationScope
public class WatchNextApplication extends Application {

    public static WatchNextApplication get(Activity context) {
        return (WatchNextApplication) context.getApplication();
    }

    private WatchNextApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerWatchNextApplicationComponent.builder().
                applicationContextModule(new ApplicationContextModule(this)).
                build();
        component.inject(this);
    }

    public WatchNextApplicationComponent getComponent() {
        return component;
    }
}
