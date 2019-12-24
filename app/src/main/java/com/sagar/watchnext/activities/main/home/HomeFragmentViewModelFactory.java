package com.sagar.watchnext.activities.main.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sagar.watchnext.network.repo.TmdbMovieRepo;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 01:52
 */

@HomeFragmentScope
public class HomeFragmentViewModelFactory implements ViewModelProvider.Factory {

    private TmdbMovieRepo movieRepo;
    private TmdbTvRepo tvRepo;

    @Inject
    HomeFragmentViewModelFactory(TmdbMovieRepo movieRepo, TmdbTvRepo tvRepo) {
        this.movieRepo = movieRepo;
        this.tvRepo = tvRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel.class)) {
            return (T) new HomeFragmentViewModel(movieRepo, tvRepo);
        } else {
            throw new IllegalStateException("unknown view model");
        }
    }

}
