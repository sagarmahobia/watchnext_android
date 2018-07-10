package com.sagar.watchnext.activities.seemoremovie;

import com.sagar.watchnext.WatchNextApplicationComponent;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 10-Jul-18. at 16:00
 */
@SeeMoreMovieActivityScope
@Component(modules = SeeMoreMovieActivityModule.class, dependencies = WatchNextApplicationComponent.class)
public interface SeeMoreMovieActivityComponent {
    void injectSeeMoreMovieActivity(SeeMoreMovieActivity seeMoreMovieActivity);

}
