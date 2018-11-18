package com.sagar.watchnext.activities.main.people;

import com.sagar.watchnext.activities.main.MainActivityComponent;

import dagger.Component;


/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 16:38
 */
@PeopleFragmentScope
@Component(modules = PeopleFragmentModule.class, dependencies = MainActivityComponent.class)
interface PeopleFragmentComponent {
    void inject(PeopleFragment peopleFragment);

    void inject(Presenter presenter);
}
