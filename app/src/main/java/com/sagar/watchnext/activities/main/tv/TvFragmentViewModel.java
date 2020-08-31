package com.sagar.watchnext.activities.main.tv;


import com.sagar.watchnext.activities.main.BaseViewModel;
import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

/**
 * Created by SAGAR MAHOBIA on 17-Feb-19. at 00:45
 */

public class TvFragmentViewModel extends BaseViewModel {

    TvFragmentViewModel(TMDBRepository tmdbRepository, TmdbTvRepo tmdbTvRepo) {
        super(tmdbRepository, tmdbTvRepo);
        load();
    }

    public void load() {
        loadTrendingShows();
        loadAiringTodayShows();
        loadOnTheAirTodayShows();
        loadPopularShows();
        loadTopRatedShows();
    }
}
