package com.sagar.watchnext.activities.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

import javax.inject.Inject;

@ListActivityScope
public class ListActivityViewModelFactory implements ViewModelProvider.Factory {


    private TMDBRepository tmdbRepository;
    private TmdbTvRepo tmdbTvRepo;

    @Inject
    ListActivityViewModelFactory(TMDBRepository tmdbRepository, TmdbTvRepo tmdbTvRepo) {
        this.tmdbRepository = tmdbRepository;
        this.tmdbTvRepo = tmdbTvRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(ListActivityViewModel.class)) {
            return (T) new ListActivityViewModel(tmdbRepository, tmdbTvRepo);
        } else {
            throw new IllegalArgumentException("Can not find ViewModel class " + modelClass.getName());
        }
    }
}


