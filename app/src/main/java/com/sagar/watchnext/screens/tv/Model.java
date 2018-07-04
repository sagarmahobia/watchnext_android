package com.sagar.watchnext.screens.tv;

import com.sagar.watchnext.network.models.tv.Show;
import com.sagar.watchnext.network.models.tv.Shows;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:15
 */
@TvFragmentScope
public class Model implements TvFragmentMvpContract.Model {

    private TmdbTvRepo tvRepo;

    @Inject
    public Model(TmdbTvRepo tvRepo) {
        this.tvRepo = tvRepo;
    }

    public List<Show> getTopRated() throws IOException {
        Shows shows = tvRepo.getTopRated().execute().body();
        if (shows != null) {
            return shows.getShows();
        }
        return null;
    }

    public List<Show> getPopular() throws IOException {
        Shows shows = tvRepo.getPopular().execute().body();
        if (shows != null) {
            return shows.getShows();
        }
        return null;
    }

    public List<Show> getOnTheAir() throws IOException {
        Shows shows = tvRepo.getOnTheAir().execute().body();
        if (shows != null) {
            return shows.getShows();
        }
        return null;
    }

    public List<Show> getAiringToday() throws IOException {
        Shows shows = tvRepo.getAiringToday().execute().body();
        if (shows != null) {
            return shows.getShows();
        }
        return null;
    }
}
