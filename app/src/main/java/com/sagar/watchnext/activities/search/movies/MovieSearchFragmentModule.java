package com.sagar.watchnext.activities.search.movies;


import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 14-Jul-18. at 00:15
 */
@Module
public class MovieSearchFragmentModule {
    private MovieSearchFragment movieSearchFragment;

    public MovieSearchFragmentModule(MovieSearchFragment movieSearchFragment) {
        this.movieSearchFragment = movieSearchFragment;
    }


    @Provides
    @MovieSearchFragmentScope
    MovieSearchFragment movieSearchFragment() {
        return this.movieSearchFragment;
    }


    @Provides
    @MovieSearchFragmentScope
    MovieSearchFragmentMvpContract.View view(MovieSearchFragment movieSearchFragment) {
        return movieSearchFragment;
    }

    @Provides
    @MovieSearchFragmentScope
    MovieSearchFragmentMvpContract.Presenter presenter(Presenter presenter) {
        return presenter;
    }

    @Provides
    @MovieSearchFragmentScope
    MovieSearchFragmentMvpContract.Model model(Model model) {
        return model;
    }


}
