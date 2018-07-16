package com.sagar.watchnext.activities.tvdetail;

import com.sagar.watchnext.WatchNextApplicationComponent;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 08-Jul-18. at 16:22
 */

@TvDetailActivityScope
@Component(modules = TvDetailActivityModule.class, dependencies = WatchNextApplicationComponent.class)
public interface TvDetailActivityComponent {
    void injectTvDetailActivity(TvDetailActivity tvDetailActivity);
}
