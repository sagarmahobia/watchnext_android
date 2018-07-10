package com.sagar.watchnext.activities.seemoretv;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 10-Jul-18. at 16:04
 */

@SeeMoreTvActivityScope
public class Presenter implements SeeMoreTvActivityMvpContract.Presenter {

    private SeeMoreTvActivityMvpContract.Model model;
    private SeeMoreTvActivityMvpContract.View view;

    @Inject
    public Presenter(SeeMoreTvActivityMvpContract.Model model, SeeMoreTvActivityMvpContract.View view) {
        this.model = model;
        this.view = view;
    }
}
