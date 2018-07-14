package com.sagar.watchnext.activities.search;

import com.sagar.watchnext.WatchNextApplicationComponent;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;
import com.sagar.watchnext.network.repo.TmdbTvRepo;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 11-Jul-18. at 18:56
 */

@Component(modules = SearchActivityModule.class, dependencies = WatchNextApplicationComponent.class)
@SearchActivityScope
public interface SearchActivityComponent {
    Picasso getPicasso();

    TmdbTvRepo getTmdbTvRepo();

    TmdbMovieRepo getTmdbMovieRepo();

    void injectSearchActivity(SearchActivity searchActivity);
}
