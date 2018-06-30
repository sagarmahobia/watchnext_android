package com.sagar.watchnext.screens.home;

import com.sagar.watchnext.screens.MainActivityComponent;
import com.sagar.watchnext.screens.movies.MoviesFragmentScope;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 16:30
 */
@Component(modules = HomeFragmentModule.class, dependencies = MainActivityComponent.class)
@MoviesFragmentScope
interface HomeFragmentComponent {
    void inject(HomeFragment  homeFragment );
}
