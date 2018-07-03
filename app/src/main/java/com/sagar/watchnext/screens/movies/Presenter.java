package com.sagar.watchnext.screens.movies;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:08
 */

@MoviesFragmentScope
public class Presenter implements MoviesFragmentMvpContract.Presenter {
    MoviesFragmentMvpContract.Model model;
    MoviesFragmentMvpContract.View view;

    @Inject
    public Presenter(MoviesFragmentMvpContract.Model model, MoviesFragmentMvpContract.View view) {
        this.model = model;
        this.view = view;
    }
}
