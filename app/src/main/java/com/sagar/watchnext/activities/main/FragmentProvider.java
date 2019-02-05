package com.sagar.watchnext.activities.main;

import com.sagar.watchnext.activities.main.home.HomeFragment;
import com.sagar.watchnext.activities.main.home.HomeFragmentModule;
import com.sagar.watchnext.activities.main.home.HomeFragmentScope;
import com.sagar.watchnext.activities.main.movies.MoviesFragment;
import com.sagar.watchnext.activities.main.movies.MoviesFragmentModule;
import com.sagar.watchnext.activities.main.movies.MoviesFragmentScope;
import com.sagar.watchnext.activities.main.people.PeopleFragment;
import com.sagar.watchnext.activities.main.people.PeopleFragmentModule;
import com.sagar.watchnext.activities.main.people.PeopleFragmentScope;
import com.sagar.watchnext.activities.main.tv.TvFragment;
import com.sagar.watchnext.activities.main.tv.TvFragmentModule;
import com.sagar.watchnext.activities.main.tv.TvFragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by SAGAR MAHOBIA on 06-Feb-19. at 00:22
 */

@Module
public abstract class FragmentProvider {


    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    @HomeFragmentScope
    abstract HomeFragment homeFragment();

    @ContributesAndroidInjector(modules = MoviesFragmentModule.class)
    @MoviesFragmentScope
    abstract MoviesFragment moviesFragment();

    @ContributesAndroidInjector(modules = TvFragmentModule.class)
    @TvFragmentScope
    abstract TvFragment tvFragment();

    @ContributesAndroidInjector(modules = PeopleFragmentModule.class)
    @PeopleFragmentScope
    abstract PeopleFragment peopleFragment();


}
