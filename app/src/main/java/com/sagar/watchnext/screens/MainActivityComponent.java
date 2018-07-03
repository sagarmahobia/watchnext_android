package com.sagar.watchnext.screens;

import com.sagar.watchnext.WatchNextApplicationComponent;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;
import com.sagar.watchnext.network.repo.TmdbPeopleRepo;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 13:37
 */
@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = WatchNextApplicationComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);

    TmdbMovieRepo getTmdbMovieRepo();

    TmdbPeopleRepo provideTmdbPeopleRepo();

    TmdbTvRepo provideTmdbTvRepo();
}
