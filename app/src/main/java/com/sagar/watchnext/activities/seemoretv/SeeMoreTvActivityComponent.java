package com.sagar.watchnext.activities.seemoretv;

import com.sagar.watchnext.WatchNextApplicationComponent;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 10-Jul-18. at 16:00
 */
@SeeMoreTvActivityScope
@Component(modules = SeeMoreTvActivityModule.class, dependencies = WatchNextApplicationComponent.class)
public interface SeeMoreTvActivityComponent {
    void injectSeeMoreTvActivity(SeeMoreTvActivity seeMoreMovieActivity);

}
