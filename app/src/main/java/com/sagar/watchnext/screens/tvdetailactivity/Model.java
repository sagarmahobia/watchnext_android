package com.sagar.watchnext.screens.tvdetailactivity;

import com.sagar.watchnext.network.models.tv.details.Details;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 08-Jul-18. at 16:25
 */

@TvDetailActivityScope
public class Model implements TvDetailActivityMvpContract.Model {
    private TmdbTvRepo tvRepo;

    @Inject
    public Model(TmdbTvRepo tvRepo) {
        this.tvRepo = tvRepo;
    }

    @Override
    public Details getTvDetail(int tvId) throws IOException {
        return tvRepo.getDetails(tvId).execute().body();
    }
}
