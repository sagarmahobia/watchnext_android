package com.sagar.watchnext;

import com.sagar.watchnext.activities.about.AboutPageActivity;
import com.sagar.watchnext.activities.about.AboutPageActivityModule;
import com.sagar.watchnext.activities.about.AboutPageActivityScope;
import com.sagar.watchnext.activities.main.FragmentProvider;
import com.sagar.watchnext.activities.main.MainActivity;
import com.sagar.watchnext.activities.main.MainActivityModule;
import com.sagar.watchnext.activities.main.MainActivityScope;
import com.sagar.watchnext.activities.moviedetail.MovieDetailActivity;
import com.sagar.watchnext.activities.moviedetail.MovieDetailActivityModule;
import com.sagar.watchnext.activities.moviedetail.MovieDetailActivityScope;
import com.sagar.watchnext.activities.search.FragmentProviderSearch;
import com.sagar.watchnext.activities.search.SearchActivity;
import com.sagar.watchnext.activities.search.SearchActivityModule;
import com.sagar.watchnext.activities.search.SearchActivityScope;
import com.sagar.watchnext.activities.tvdetail.TvDetailActivity;
import com.sagar.watchnext.activities.tvdetail.TvDetailActivityModule;
import com.sagar.watchnext.activities.tvdetail.TvDetailActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by SAGAR MAHOBIA on 27-Jan-19. at 01:52
 */

@Module
abstract class ActivityProvider {

    @ContributesAndroidInjector(modules = {MainActivityModule.class, FragmentProvider.class})
    @MainActivityScope
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = {SearchActivityModule.class, FragmentProviderSearch.class})
    @SearchActivityScope
    abstract SearchActivity searchActivity();

    @ContributesAndroidInjector(modules = {TvDetailActivityModule.class})
    @TvDetailActivityScope
    abstract TvDetailActivity tvDetailActivity();

    @ContributesAndroidInjector(modules = {MovieDetailActivityModule.class})
    @MovieDetailActivityScope
    abstract MovieDetailActivity movieDetailActivity();

    @ContributesAndroidInjector(modules = {AboutPageActivityModule.class})
    @AboutPageActivityScope
    abstract AboutPageActivity aboutPageActivity();

}
