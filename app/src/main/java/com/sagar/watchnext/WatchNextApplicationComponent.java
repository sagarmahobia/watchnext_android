package com.sagar.watchnext;

import com.sagar.watchnext.network.repo.TmdbMovieRepo;
import com.sagar.watchnext.network.repo.TmdbPeopleRepo;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 12:46
 */

@ApplicationScope
@Component(modules = {ApplicationContextModule.class, NetworkModule.class})
public interface WatchNextApplicationComponent {

    void inject(WatchNextApplication watchNextApplication);

    TmdbMovieRepo provideTmdbMovieRepo();

    TmdbPeopleRepo provideTmdbPeopleRepo();
}
