package com.sagar.watchnext.activities.main.tv;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.sagar.watchnext.network.repo.TmdbTvRepo;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 17-Feb-19. at 00:44
 */

@TvFragmentScope
public class TvFragmentViewModelFactory implements ViewModelProvider.Factory {

    private TmdbTvRepo tvRepo;

    @Inject
    TvFragmentViewModelFactory(TmdbTvRepo tvRepo) {
        this.tvRepo = tvRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TvFragmentViewModel.class)) {
            return (T) new TvFragmentViewModel(tvRepo);
        } else {
            throw new IllegalStateException("unknown view model");
        }
    }
}
