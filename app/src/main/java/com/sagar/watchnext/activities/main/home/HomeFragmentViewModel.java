package com.sagar.watchnext.activities.main.home;

import com.sagar.watchnext.activities.main.BaseViewModel;
import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 01:47
 */

public class HomeFragmentViewModel extends BaseViewModel {

    HomeFragmentViewModel(TMDBRepository tmdbRepository, TmdbTvRepo tmdbTvRepo) {
        super(tmdbRepository, tmdbTvRepo);
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

        loadNetflixShows();
        loadAmazonPrimeShows();
        loadAppleTvShows();
        loadDisneyPlusShows();

        loadPopularPeoples();

    }

}
