package com.sagar.watchnext.screens.movies;

import com.sagar.watchnext.screens.MainActivityComponent;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 16:33
 */

@MoviesFragmentScope
@Component(modules = MoviesFragmentModule.class, dependencies = MainActivityComponent.class)
public interface MoviesFragmentComponent {

    void inject(MoviesFragment moviesFragment);
}
