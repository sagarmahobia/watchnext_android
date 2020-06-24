package com.sagar.watchnext.activities.main.tv;


import com.sagar.watchnext.activities.main.BaseViewModel;
import com.sagar.watchnext.network.repo.TMDBRepository;

/**
 * Created by SAGAR MAHOBIA on 17-Feb-19. at 00:45
 */

public class TvFragmentViewModel extends BaseViewModel {

    TvFragmentViewModel(TMDBRepository tmdbRepository) {
        super(tmdbRepository);
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
