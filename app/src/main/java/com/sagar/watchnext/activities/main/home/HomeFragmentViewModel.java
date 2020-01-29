package com.sagar.watchnext.activities.main.home;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.network.newmodels.CardItem;
import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;
import com.sagar.watchnext.network.repo.TmdbTvRepo;
import com.sagar.watchnext.response.Response;
import com.sagar.watchnext.utils.ImageUrlUtil;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 01:47
 */
public class HomeFragmentViewModel extends ViewModel {

    private TmdbMovieRepo movieRepo;
    private TmdbTvRepo tvRepo;
    private TMDBRepository tmdbRepository;

    private List<CardModel> movies = new ArrayList<>();
    private List<CardModel> shows = new ArrayList<>();

    private MutableLiveData<Response> moviesLiveData = new MutableLiveData<>();
    private MutableLiveData<Response> showsLiveData = new MutableLiveData<>();

    private CardRecyclerModel moviesCardRecyclerModel = new CardRecyclerModel();
    private CardRecyclerModel tvShowsCardRecyclerModel = new CardRecyclerModel();

    private CompositeDisposable disposables = new CompositeDisposable();

    HomeFragmentViewModel(TmdbMovieRepo movieRepo, TmdbTvRepo tvRepo, TMDBRepository tmdbRepository) {

        this.movieRepo = movieRepo;
        this.tvRepo = tvRepo;
        this.tmdbRepository = tmdbRepository;

        load();
    }

    MutableLiveData<Response> getMoviesLiveData() {
        return moviesLiveData;
    }

    MutableLiveData<Response> getShowsLiveData() {
        return showsLiveData;
    }

    CardRecyclerModel getMoviesCardRecyclerModel() {
        return moviesCardRecyclerModel;
    }

    CardRecyclerModel getTvShowsCardRecyclerModel() {
        return tvShowsCardRecyclerModel;
    }

    public void load() {
        disposables.add(tmdbRepository.getFirstPage("movie", "now_playing").subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                            this.movies.clear();
                            for (CardItem movie : movies.getCardItems()) {
                                CardModel cardModel = new CardModel();
                                cardModel.setId(movie.getId());
                                cardModel.setImageUrl(ImageUrlUtil.getPosterImageUrl(movie.getPosterPath()));
                                cardModel.setTitle(movie.getTitle());
                                this.movies.add(cardModel);
                            }
                            moviesLiveData.setValue(Response.success(this.movies));
                        }, e -> moviesLiveData.setValue(Response.error(e))
                ));


        disposables.add(tmdbRepository.getFirstPage("tv", "on_the_air").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shows -> {
                            this.shows.clear();
                            for (CardItem show : shows.getCardItems()) {
                                CardModel cardModel = new CardModel();
                                cardModel.setId(show.getId());
                                cardModel.setImageUrl(ImageUrlUtil.getPosterImageUrl(show.getPosterPath()));
                                cardModel.setTitle(show.getTitle());
                                this.shows.add(cardModel);
                            }
                            showsLiveData.setValue(Response.success(this.shows));
                        }, e -> showsLiveData.setValue(Response.error(e))
                ));

    }

    @Override
    public void onCleared() {
        super.onCleared();
        disposables.dispose();
    }

}
