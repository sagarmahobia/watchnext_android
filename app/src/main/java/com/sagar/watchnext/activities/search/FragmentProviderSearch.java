package com.sagar.watchnext.activities.search;

import com.sagar.watchnext.activities.search.movies.MovieSearchFragment;
import com.sagar.watchnext.activities.search.movies.MovieSearchFragmentModule;
import com.sagar.watchnext.activities.search.movies.MovieSearchFragmentScope;
import com.sagar.watchnext.activities.search.tv.TvSearchFragment;
import com.sagar.watchnext.activities.search.tv.TvSearchFragmentModule;
import com.sagar.watchnext.activities.search.tv.TvSearchFragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by SAGAR MAHOBIA on 06-Feb-19. at 00:24
 */

@Module
public abstract class FragmentProviderSearch {

    @ContributesAndroidInjector(modules = TvSearchFragmentModule.class)
    @TvSearchFragmentScope
    abstract TvSearchFragment tvSearchFragment();

    @ContributesAndroidInjector(modules = MovieSearchFragmentModule.class)
    @MovieSearchFragmentScope
    abstract MovieSearchFragment movieSearchFragment();

}
