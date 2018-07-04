package com.sagar.watchnext.screens.movies;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 16:33
 */
@Module
public class MoviesFragmentModule {

    private MoviesFragment moviesFragment;

    MoviesFragmentModule(MoviesFragment moviesFragment) {
        this.moviesFragment = moviesFragment;
    }

    @Provides
    @MoviesFragmentScope
    MoviesFragment getMoviesFragment() {
        return this.moviesFragment;
    }

    @Provides
    @MoviesFragmentScope
    MoviesFragmentMvpContract.View getView(MoviesFragment moviesFragment) {
        return moviesFragment;
    }

    @Provides
    @MoviesFragmentScope
    MoviesFragmentMvpContract.Model getModel(Model model) {
        return model;
    }

    @Provides
    @MoviesFragmentScope
    MoviesFragmentMvpContract.Presenter getPresenter(Presenter presenter) {
        return presenter;
    }

}
