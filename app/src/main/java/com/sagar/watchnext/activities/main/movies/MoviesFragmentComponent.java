package com.sagar.watchnext.activities.main.movies;

import com.sagar.watchnext.activities.main.MainActivityComponent;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 16:33
 */

@MoviesFragmentScope
@Component(modules = MoviesFragmentModule.class, dependencies = {MainActivityComponent.class})
public interface MoviesFragmentComponent {

    void inject(MoviesFragment moviesFragment);

    void inject(Presenter presenter);
}
