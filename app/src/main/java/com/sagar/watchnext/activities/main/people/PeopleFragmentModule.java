package com.sagar.watchnext.activities.main.people;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 16:37
 */
@Module
class PeopleFragmentModule {

    private PeopleFragment peopleFragment;

      PeopleFragmentModule(PeopleFragment peopleFragment) {
        this.peopleFragment = peopleFragment;
    }

    @Provides
    @PeopleFragmentScope
    PeopleFragment getPeopleFragment() {
        return this.peopleFragment;
    }


    @Provides
    @PeopleFragmentScope
    PeopleFragmentMvpContract.View getView(PeopleFragment peopleFragment) {
        return peopleFragment;
    }

    @Provides
    @PeopleFragmentScope
    PeopleFragmentMvpContract.Model getModel(Model model) {
        return model;
    }

    @Provides
    @PeopleFragmentScope
    PeopleFragmentMvpContract.Presenter getPresenter(Presenter presenter) {
        return presenter;
    }


}
