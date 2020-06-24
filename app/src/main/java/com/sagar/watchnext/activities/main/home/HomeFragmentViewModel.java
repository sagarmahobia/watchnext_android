package com.sagar.watchnext.activities.main.home;

import com.sagar.watchnext.activities.main.BaseViewModel;
import com.sagar.watchnext.network.repo.TMDBRepository;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 01:47
 */

public class HomeFragmentViewModel extends BaseViewModel {

    HomeFragmentViewModel(TMDBRepository tmdbRepository) {
        super(tmdbRepository);
        load();
    }

    public void load() {
        loadTrendingMovies();
        loadInTheatersMovies();
        loadUpcomingMovies();
        loadPopularMovies();
        loadTopRatedMovies();

        loadTrendingShows();
        loadAiringTodayShows();
        loadOnTheAirTodayShows();
        loadPopularShows();
        loadTopRatedShows();

    }

}
