package com.sagar.watchnext.activities.main.movies;


import com.sagar.watchnext.activities.main.BaseViewModel;
import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 20:36
 */
public class MoviesFragmentViewModel extends BaseViewModel {

    MoviesFragmentViewModel(TMDBRepository tmdbRepository, TmdbTvRepo tmdbTvRepo) {
        super(tmdbRepository, tmdbTvRepo);
        load();
    }


    public void load() {
        loadTrendingMovies();
        loadInTheatersMovies();
        loadUpcomingMovies();
        loadPopularMovies();
        loadTopRatedMovies();
    }
}
