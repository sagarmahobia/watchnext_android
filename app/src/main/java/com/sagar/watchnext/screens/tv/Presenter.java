package com.sagar.watchnext.screens.tv;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:15
 */
@TvFragmentScope
public class Presenter implements TvFragmentMvpContract.Presenter {
    TvFragmentMvpContract.Model model;
    TvFragmentMvpContract.View view;

    @Inject
    public Presenter(TvFragmentMvpContract.Model model, TvFragmentMvpContract.View view) {
        this.model = model;
        this.view = view;
    }
}
