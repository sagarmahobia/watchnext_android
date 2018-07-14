package com.sagar.watchnext.activities.main.home;

import com.sagar.watchnext.adapters.Card;
import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.models.tv.Show;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

import java.util.List;

import javax.inject.Inject;

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

    private TmdbMovieRepo movieRepo;
    private TmdbTvRepo tvRepo;

    @Inject
    public Presenter(HomeFragmentMvpContract.View view, HomeFragmentMvpContract.Model model, TmdbMovieRepo movieRepo, TmdbTvRepo tvRepo) {
        this.view = view;
        this.model = model;
        this.movieRepo = movieRepo;
        this.tvRepo = tvRepo;
        disposables = new CompositeDisposable();
    }

    @Override
    public void onCreate() {


        disposables.add(movieRepo.getInTheaterMovies().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> {
                    this.movies = movies.getMovies();
                    view.onSucceedLoadingList(ListType.InTheaters);
                }, e -> {
                    view.onErrorLoadingList(ListType.InTheaters);
                }));


        disposables.add(tvRepo.getOnTheAir().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shows -> {
                    this.shows = shows.getShows();
                    view.onSucceedLoadingList(ListType.OnTv);
                }, e -> {
                    view.onErrorLoadingList(ListType.OnTv);
                }));

    }

    @Override
    public void loadMore(ListType listType, int pageToLoad) {
        switch (listType) {
            case InTheaters:
                disposables.add(movieRepo.getInTheaterMovies(pageToLoad).
                        subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(movieList -> {
                            this.movies.addAll(movieList.getMovies());
                            view.notifyAdaptersNewData(listType);
                        }));
                break;
            case OnTv:
                disposables.add(tvRepo.getOnTheAir(pageToLoad).
                        subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(tvList -> {
                            this.shows.addAll(tvList.getShows());
                            view.notifyAdaptersNewData(listType);
                        }));
                break;
        }
    }


    //combined

    @Override
    public int getCardsCount(ListType listType) {
        switch (listType) {
            case InTheaters:
                return movies.size();
            default:
                return shows.size();
        }

    }

    @Override
    public void onBindCard(ListType listType, Card card, int position) {
        switch (listType.getContentType()) {
            case MOVIE:
                Movie movie = movies.get(position);
                card.setTitle(movie.getTitle());
                card.setImage(movie.getPosterPath());
                break;
            default:
                Show show = shows.get(position);
                card.setTitle(show.getName());
                card.setImage(show.getPosterPath());
        }
    }

    @Override
    public void onRecyclerItemClick(ListType listType, int position) {
        switch (listType.getContentType()) {
            case MOVIE:
                int movieId = movies.get(position).getId();
                view.startMovieDetailActivity(movieId);
                break;
            default:
                int tvId = shows.get(position).getId();
                view.startTvDetailActivity(tvId);
                break;
        }
    }


    @Override
    public void onDestroy() {
        disposables.dispose();
    }
}
