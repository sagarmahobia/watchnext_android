package com.sagar.watchnext.activities.main.movies;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sagar.watchnext.network.repo.TmdbMovieRepo;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 20:20
 */

@MoviesFragmentScope
public class MoviesFragmentViewModelFactory implements ViewModelProvider.Factory {

    private TmdbMovieRepo movieRepo;

    @Inject
    MoviesFragmentViewModelFactory(TmdbMovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoviesFragmentViewModel.class)) {
            return (T) new MoviesFragmentViewModel(movieRepo);
        } else {
            throw new IllegalStateException("unknown view model");
        }
    }
}
