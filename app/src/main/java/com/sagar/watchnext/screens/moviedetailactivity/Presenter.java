package com.sagar.watchnext.screens.moviedetailactivity;

import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.models.movies.moviedetail.MovieDetail;
import com.sagar.watchnext.network.models.movies.videos.Video;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SAGAR MAHOBIA on 06-Jul-18. at 16:23
 */
@MovieDetailActivityScope
public class Presenter implements MovieDetailActivityMvpContract.Presenter {

    private MovieDetailActivityMvpContract.View view;
    private MovieDetailActivityMvpContract.Model model;
    private CompositeDisposable disposable;

    private List<Movie> recommendations;
    private List<Movie> similars;
    private List<Video> videos;


    @Inject
    public Presenter(MovieDetailActivityMvpContract.View view, MovieDetailActivityMvpContract.Model model) {
        this.view = view;
        this.model = model;
        disposable = new CompositeDisposable();
    }

    @Override
    public void onCreate(int movieId) {
        Single<MovieDetail> movieDetailSingle = Single.create(e -> {
            try {
                e.onSuccess(model.getMovieDetail(movieId));
            } catch (IOException error) {
                e.onError(error);
            }
        });

        disposable.add(movieDetailSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movieDetail -> {
                    view.onSucceedLoadingMovieDetail(movieDetail);
                }, error -> {
                    view.onErrorLoadingMovieDetail();
                }));

        Single<List<Movie>> recommendationSingle = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getRecommendations(movieId));
            } catch (IOException error) {
                emitter.onError(error);
            }
        });

        disposable.add(recommendationSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(recommendations -> {
                    this.recommendations = recommendations;
                    //todo notify view
                }, error -> {
                    //todo notify view
                })
        );


        Single<List<Movie>> similarSingle = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getSimilars(movieId));
            } catch (IOException error) {
                emitter.onError(error);
            }
        });

        disposable.add(similarSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(similars -> {
                    this.similars = similars;
                    //todo notify view
                }, error -> {
                    //todo notify view
                })
        );

        Single<List<Video>> videosSingle = Single.create(emitter -> {
            try {
                emitter.onSuccess(model.getVideos(movieId));
            } catch (IOException error) {
                emitter.onError(error);
            }
        });

        disposable.add(videosSingle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(videos -> {
                    this.videos = videos;
                    //todo notify view
                }, error -> {
                    //todo notify view
                })
        );
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
    }
}
