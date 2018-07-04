package com.sagar.watchnext.screens.tv;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 16:42
 */

@Module
class TvFragmentModule {

    private TvFragment tvFragment;

    TvFragmentModule(TvFragment tvFragment) {
        this.tvFragment = tvFragment;
    }

    @TvFragmentScope
    @Provides
    TvFragment getTvFragment() {
        return tvFragment;
    }


    @TvFragmentScope
    @Provides
    TvFragmentMvpContract.View getView(TvFragment tvFragment) {
        return tvFragment;
    }


    @TvFragmentScope
    @Provides
    TvFragmentMvpContract.Model getModel(Model model) {
        return model;
    }

    @TvFragmentScope
    @Provides
    TvFragmentMvpContract.Presenter getPresenter(Presenter presenter) {
        return presenter;
    }

}
