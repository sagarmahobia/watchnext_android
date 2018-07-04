package com.sagar.watchnext.screens.home;

import com.sagar.watchnext.adapters.Card;
import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.models.tv.Show;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:10
 */

@HomeFragmentScope
public class Presenter implements HomeFragmentMvpContract.Presenter {

    private HomeFragmentMvpContract.View view;
    private HomeFragmentMvpContract.Model model;
    private List<Movie> movies;
    private List<Show> shows;

    private CompositeDisposable disposables;

    @Inject
    public Presenter(HomeFragmentMvpContract.View view, HomeFragmentMvpContract.Model model) {
        this.view = view;
        this.model = model;
        disposables = new CompositeDisposable();
    }

    @Override
    public void onCreate() {

        Single<List<Movie>> singleMovie = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getNowPlayingMovies());
            } catch (IOException e) {
                emitter.onError(e);
            }
        });
        disposables.add(singleMovie.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    this.movies = movies;
                    view.onSucceedLoadingMovieList();
                }, e -> {
                    view.onErrorLoadingMovieList();
                }));


        Single<List<Show>> singleTv = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getOnTheAirTv());
            } catch (IOException e) {
                emitter.onError(e);
            }
        });
        disposables.add(singleTv.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shows -> {
                    this.shows = shows;
                    view.onSucceedLoadingTvList();
                }, e -> {
                    view.onErrorLoadingTvList();
                }));

    }

    ////movie methods
    @Override
    public int getMovieCardsCount() {
        return movies.size();
    }

    @Override
    public void onBindMovieCard(Card card, int position) {
        Movie movie = movies.get(position);
        card.setTitle(movie.getTitle());
        card.setImage(movies.get(position).getPosterPath());

    }

    @Override
    public void onMovieRecyclerItemClick(int position) {
        //todo modify on click listener
        view.showToast(movies.get(position).getTitle() + " was clicked");

    }

    ////Tv  methods
    @Override
    public int getTvCardsCount() {
        return shows.size();
    }

    @Override
    public void onBindTvCard(Card card, int position) {
        Show show = shows.get(position);
        card.setTitle(show.getName());
        card.setImage(shows.get(position).getPosterPath());
    }

    @Override
    public void onTvRecyclerItemClick(int position) {
        //todo modify on click listener
        view.showToast(shows.get(position).getName() + " was clicked");
    }


    @Override
    public void onDestroy() {
        disposables.dispose();
    }
}
