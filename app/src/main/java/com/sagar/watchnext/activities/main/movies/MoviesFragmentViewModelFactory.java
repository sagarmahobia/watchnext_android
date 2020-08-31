package com.sagar.watchnext.activities.main.movies;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 20:20
 */

@MoviesFragmentScope
public class MoviesFragmentViewModelFactory implements ViewModelProvider.Factory {

    private TMDBRepository tmdbRepository;
    private TmdbTvRepo tmdbTvRepo;

    @Inject
    MoviesFragmentViewModelFactory(TMDBRepository tmdbRepository, TmdbTvRepo tmdbTvRepo) {
        this.tmdbRepository = tmdbRepository;
        this.tmdbTvRepo = tmdbTvRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoviesFragmentViewModel.class)) {
            return (T) new MoviesFragmentViewModel(tmdbRepository, tmdbTvRepo);
        } else {
            throw new IllegalStateException("unknown view model");
        }
    }
}
