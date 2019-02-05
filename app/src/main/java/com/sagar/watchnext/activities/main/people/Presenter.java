package com.sagar.watchnext.activities.main.people;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:13
 */

@PeopleFragmentScope
public class Presenter implements Contract.Presenter {

    private Contract.View view;

    @Inject
    public Presenter(Contract.View view) {
        this.view = view;
    }
}
