package com.sagar.watchnext.activities.seemoremovie;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 10-Jul-18. at 15:58
 */

@Module
public class SeeMoreMovieActivityModule {
    private SeeMoreMovieActivity seeMoreMovieActivity;

    public SeeMoreMovieActivityModule(SeeMoreMovieActivity seeMoreMovieActivity) {
        this.seeMoreMovieActivity = seeMoreMovieActivity;
    }

    @Provides
    @SeeMoreMovieActivityScope
    SeeMoreMovieActivity provideSeeMoreMovieActivity() {
        return seeMoreMovieActivity;
    }

    @Provides
    @SeeMoreMovieActivityScope
    SeeMoreMovieActivityMvpContract.View provideView(SeeMoreMovieActivity seeMoreMovieActivity) {
        return seeMoreMovieActivity;
    }


    @Provides
    @SeeMoreMovieActivityScope
    SeeMoreMovieActivityMvpContract.Presenter providePresenter(Presenter presenter) {
        return presenter;
    }

    @Provides
    @SeeMoreMovieActivityScope
    SeeMoreMovieActivityMvpContract.Model provideModel(Model model) {
        return model;
    }
}
