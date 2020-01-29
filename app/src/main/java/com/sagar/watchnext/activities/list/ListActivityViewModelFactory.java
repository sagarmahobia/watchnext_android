package com.sagar.watchnext.activities.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sagar.watchnext.network.repo.TMDBRepository;

import javax.inject.Inject;

@ListActivityScope
public class ListActivityViewModelFactory implements ViewModelProvider.Factory {


    private TMDBRepository tmdbRepository;

    @Inject
    ListActivityViewModelFactory(TMDBRepository tmdbRepository) {
        this.tmdbRepository = tmdbRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(ListActivityViewModel.class)) {
            return (T) new ListActivityViewModel(tmdbRepository);
        } else {
            throw new IllegalArgumentException("Can not find ViewModel class " + modelClass.getName());
        }
    }
}


