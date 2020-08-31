package com.sagar.watchnext.activities.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sagar.watchnext.network.repo.TMDBRepository
import com.sagar.watchnext.network.repo.TmdbTvRepo
import javax.inject.Inject

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 01:52
 */
@HomeFragmentScope
class HomeFragmentViewModelFactory @Inject internal constructor(private val tmdbRepository: TMDBRepository, private val tmdbTvRepo: TmdbTvRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel(tmdbRepository, tmdbTvRepo) as T
        } else {
            throw IllegalStateException("unknown view model")
        }
    }

}


/*
POSSIBLE JAVA EQUIVALENT

public class MoviesFragmentViewModelFactory implements ViewModelProvider.Factory {

    private TmdbMovieRepo movieRepo;
    private TMDBRepository tmdbRepository;

    @Inject
    MoviesFragmentViewModelFactory(TmdbMovieRepo movieRepo, TMDBRepository tmdbRepository) {
        this.movieRepo = movieRepo;
        this.tmdbRepository = tmdbRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoviesFragmentViewModel.class)) {
            return (T) new MoviesFragmentViewModel(tmdbRepository);
        } else {
            throw new IllegalStateException("unknown view model");
        }
    }
}*/