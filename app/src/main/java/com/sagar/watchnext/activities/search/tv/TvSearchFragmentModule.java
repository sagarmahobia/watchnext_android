package com.sagar.watchnext.activities.search.tv;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 12-Jul-18. at 14:01
 */

@Module
public class TvSearchFragmentModule {

    @Provides
    @TvSearchFragmentScope
    Contract.View view(TvSearchFragment tvSearchFragment) {
        return tvSearchFragment;
    }

    @Provides
    @TvSearchFragmentScope
    Contract.Presenter presenter(Presenter presenter) {
        return presenter;
    }

}
