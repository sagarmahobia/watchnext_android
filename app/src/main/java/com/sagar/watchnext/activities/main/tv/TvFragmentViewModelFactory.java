package com.sagar.watchnext.activities.main.tv;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sagar.watchnext.network.repo.TMDBRepository;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 17-Feb-19. at 00:44
 */

@TvFragmentScope
public class TvFragmentViewModelFactory implements ViewModelProvider.Factory {

    private TMDBRepository tmdbRepository;

    @Inject
    TvFragmentViewModelFactory(TMDBRepository tmdbRepository) {
        this.tmdbRepository = tmdbRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TvFragmentViewModel.class)) {
            return (T) new TvFragmentViewModel(tmdbRepository);
        } else {
            throw new IllegalStateException("unknown view model");
        }
    }
}
