package com.sagar.watchnext.activities.main.movies;


import com.sagar.watchnext.activities.main.BaseViewModel;
import com.sagar.watchnext.network.repo.TMDBRepository;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 20:36
 */
public class MoviesFragmentViewModel extends BaseViewModel {

    MoviesFragmentViewModel(TMDBRepository tmdbRepository) {
        super(tmdbRepository);
        load();
    }


    public void load() {
        loadInTheatersMovies();
        loadUpcomingMovies();
        loadPopularMovies();
        loadTopRatedMovies();
    }
}
