package com.sagar.watchnext.activities.search.movies;

import com.sagar.watchnext.activities.search.SearchActivityComponent;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 14-Jul-18. at 00:17
 */

@MovieSearchFragmentScope
@Component(modules = MovieSearchFragmentModule.class, dependencies = SearchActivityComponent.class)
public interface MovieSearchFragmentComponent {
    void inject(MovieSearchFragment movieSearchFragment);

    void inject(Presenter presenter);
}
