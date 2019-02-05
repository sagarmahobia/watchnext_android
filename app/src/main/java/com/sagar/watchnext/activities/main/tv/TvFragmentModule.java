package com.sagar.watchnext.activities.main.tv;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 16:42
 */

@Module
public class TvFragmentModule {


    @TvFragmentScope
    @Provides
    Contract.View getView(TvFragment tvFragment) {
        return tvFragment;
    }

    @TvFragmentScope
    @Provides
    Contract.Presenter getPresenter(Presenter presenter) {
        return presenter;
    }

}
