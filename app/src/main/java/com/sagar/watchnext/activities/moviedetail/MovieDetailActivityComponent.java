package com.sagar.watchnext.activities.moviedetail;

import com.sagar.watchnext.WatchNextApplicationComponent;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 06-Jul-18. at 16:13
 */

@MovieDetailActivityScope
@Component(modules = MovieDetailActivityModule.class, dependencies = WatchNextApplicationComponent.class)
public interface MovieDetailActivityComponent {
    void injectMovieDetailActivity(MovieDetailActivity activity);
}
