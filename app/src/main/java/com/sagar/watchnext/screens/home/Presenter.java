package com.sagar.watchnext.screens.home;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:10
 */

@HomeFragmentScope
public class Presenter implements HomeFragmentMvpContract.Presenter {

    private HomeFragmentMvpContract.View view;
    private HomeFragmentMvpContract.Model model;

    @Inject
    public Presenter(HomeFragmentMvpContract.View view, HomeFragmentMvpContract.Model model) {
        this.view = view;
        this.model = model;
    }
}
