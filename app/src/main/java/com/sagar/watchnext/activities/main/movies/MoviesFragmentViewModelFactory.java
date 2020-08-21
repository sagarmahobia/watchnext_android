package com.sagar.watchnext.activities.main.movies;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sagar.watchnext.network.repo.TMDBRepository;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 20:20
 */

@MoviesFragmentScope
public class MoviesFragmentViewModelFactory implements ViewModelProvider.Factory {

    private TMDBRepository tmdbRepository;

    @Inject
    MoviesFragmentViewModelFactory(TMDBRepository tmdbRepository) {
        this.tmdbRepository = tmdbRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoviesFragmentViewModel.class)) {
            return (T) new MoviesFragmentViewModel(tmdbRepository);
        } else {
            throw new IllegalStateException("unknown view model");
        }
    }
}
