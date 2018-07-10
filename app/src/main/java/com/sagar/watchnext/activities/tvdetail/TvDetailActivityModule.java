package com.sagar.watchnext.activities.tvdetail;


import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 08-Jul-18. at 16:21
 */
@Module
public class TvDetailActivityModule {

    private TvDetailActivity tvDetailActivity;

    public TvDetailActivityModule(TvDetailActivity tvDetailActivity) {
        this.tvDetailActivity = tvDetailActivity;
    }

    @Provides
    @TvDetailActivityScope
    TvDetailActivity provideTvDetailActivity() {
        return tvDetailActivity;
    }

    @Provides
    @TvDetailActivityScope
    TvDetailActivityMvpContract.View provideView(TvDetailActivity tvDetailActivity) {
        return tvDetailActivity;
    }


    @Provides
    @TvDetailActivityScope
    TvDetailActivityMvpContract.Presenter providePresenter(Presenter presenter) {
        return presenter;
    }

    @Provides
    @TvDetailActivityScope
    TvDetailActivityMvpContract.Model provideModel(Model model) {
        return model;
    }

}
