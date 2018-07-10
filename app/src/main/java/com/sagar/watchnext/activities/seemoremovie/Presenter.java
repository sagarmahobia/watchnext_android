package com.sagar.watchnext.activities.seemoremovie;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 10-Jul-18. at 16:04
 */

@SeeMoreMovieActivityScope
public class Presenter implements SeeMoreMovieActivityMvpContract.Presenter {

    private SeeMoreMovieActivityMvpContract.Model model;
    private SeeMoreMovieActivityMvpContract.View view;

    @Inject
    public Presenter(SeeMoreMovieActivityMvpContract.Model model, SeeMoreMovieActivityMvpContract.View view) {
        this.model = model;
        this.view = view;
    }
}
