package com.sagar.watchnext.activities.seemoretv;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 10-Jul-18. at 15:58
 */

@Module
public class SeeMoreTvActivityModule {
    private SeeMoreTvActivity seeMoreMovieActivity;

    public SeeMoreTvActivityModule(SeeMoreTvActivity seeMoreMovieActivity) {
        this.seeMoreMovieActivity = seeMoreMovieActivity;
    }

    @Provides
    @SeeMoreTvActivityScope
    SeeMoreTvActivity provideSeeMoreMovieActivity() {
        return seeMoreMovieActivity;
    }

    @Provides
    @SeeMoreTvActivityScope
    SeeMoreTvActivityMvpContract.View provideView(SeeMoreTvActivity seeMoreMovieActivity) {
        return seeMoreMovieActivity;
    }


    @Provides
    @SeeMoreTvActivityScope
    SeeMoreTvActivityMvpContract.Presenter providePresenter(Presenter presenter) {
        return presenter;
    }

    @Provides
    @SeeMoreTvActivityScope
    SeeMoreTvActivityMvpContract.Model provideModel(Model model) {
        return model;
    }
}
