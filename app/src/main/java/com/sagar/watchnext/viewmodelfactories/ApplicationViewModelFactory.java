package com.sagar.watchnext.viewmodelfactories;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sagar.watchnext.ApplicationScope;
import com.sagar.watchnext.activities.moviedetail.MovieDetailActivityViewModel;
import com.sagar.watchnext.activities.tvdetail.TvDetailActivityViewModel;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;
import com.sagar.watchnext.network.repo.TmdbPeopleRepo;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

import javax.inject.Inject;

@ApplicationScope
public class ApplicationViewModelFactory implements ViewModelProvider.Factory {

    private TmdbMovieRepo tmdbMovieRepo;
    private TmdbTvRepo tmdbTvRepo;
    private TmdbPeopleRepo tmdbPeopleRepo;


    @Inject
    public ApplicationViewModelFactory(TmdbMovieRepo tmdbMovieRepo, TmdbTvRepo tmdbTvRepo, TmdbPeopleRepo tmdbPeopleRepo) {
        this.tmdbMovieRepo = tmdbMovieRepo;
        this.tmdbTvRepo = tmdbTvRepo;
        this.tmdbPeopleRepo = tmdbPeopleRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieDetailActivityViewModel.class)) {
            return (T) new MovieDetailActivityViewModel(tmdbMovieRepo);

        } else if (modelClass.isAssignableFrom(TvDetailActivityViewModel.class)) {
            return (T) new TvDetailActivityViewModel(tmdbTvRepo);

        } else {
            throw new IllegalArgumentException("Can not find view model class " + modelClass.getName());
        }
    }
}
