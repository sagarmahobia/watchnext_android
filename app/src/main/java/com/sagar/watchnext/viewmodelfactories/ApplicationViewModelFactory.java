package com.sagar.watchnext.viewmodelfactories;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sagar.watchnext.ApplicationScope;
import com.sagar.watchnext.activities.moviedetail.MovieDetailActivityViewModel;
import com.sagar.watchnext.activities.tvdetail.TvDetailActivityViewModel;
import com.sagar.watchnext.network.repo.TMDBRepository;

import javax.inject.Inject;

@ApplicationScope
public class ApplicationViewModelFactory implements ViewModelProvider.Factory {

    private TMDBRepository tmdbRepository;

    @Inject
    public ApplicationViewModelFactory(TMDBRepository tmdbRepository) {
        this.tmdbRepository = tmdbRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieDetailActivityViewModel.class)) {
            return (T) new MovieDetailActivityViewModel(tmdbRepository);

        } else if (modelClass.isAssignableFrom(TvDetailActivityViewModel.class)) {
            return (T) new TvDetailActivityViewModel(tmdbRepository);

        } else {
            throw new IllegalArgumentException("Can not find view model class " + modelClass.getName());
        }
    }
}
