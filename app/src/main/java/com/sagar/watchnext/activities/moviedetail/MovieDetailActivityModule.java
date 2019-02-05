package com.sagar.watchnext.activities.moviedetail;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 06-Jul-18. at 16:15
 */
@Module
public class MovieDetailActivityModule {

    @MovieDetailActivityScope
    @Provides
    Contract.View getView(MovieDetailActivity movieDetailActivity) {
        return movieDetailActivity;
    }

    @MovieDetailActivityScope
    @Provides
    Contract.Presenter getPresenter(Presenter presenter) {
        return presenter;
    }
}

