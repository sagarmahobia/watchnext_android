package com.sagar.watchnext.activities.tvdetail;


import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 08-Jul-18. at 16:21
 */
@Module
public class TvDetailActivityModule {

    @Provides
    @TvDetailActivityScope
    Contract.View provideView(TvDetailActivity tvDetailActivity) {
        return tvDetailActivity;
    }

    @Provides
    @TvDetailActivityScope
    Contract.Presenter providePresenter(Presenter presenter) {
        return presenter;
    }


}
