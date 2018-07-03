package com.sagar.watchnext.screens.people;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:13
 */

@PeopleFragmentScope
public class Presenter implements PeopleFragmentMvpContract.Presenter {
    PeopleFragmentMvpContract.Model model;
    PeopleFragmentMvpContract.View view;

    @Inject
    public Presenter(PeopleFragmentMvpContract.Model model, PeopleFragmentMvpContract.View view) {
        this.model = model;
        this.view = view;
    }
}
