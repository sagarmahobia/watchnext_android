package com.sagar.watchnext.activities.main.tv;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 17-Feb-19. at 00:44
 */

@TvFragmentScope
public class TvFragmentViewModelFactory implements ViewModelProvider.Factory {

    private TMDBRepository tmdbRepository;
    private TmdbTvRepo tmdbTvRepo;

    @Inject
    TvFragmentViewModelFactory(TMDBRepository tmdbRepository, TmdbTvRepo tmdbTvRepo) {
        this.tmdbRepository = tmdbRepository;
        this.tmdbTvRepo = tmdbTvRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TvFragmentViewModel.class)) {
            return (T) new TvFragmentViewModel(tmdbRepository, tmdbTvRepo);
        } else {
            throw new IllegalStateException("unknown view model");
        }
    }
}
