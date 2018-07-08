package com.sagar.watchnext.screens.moviedetailactivity;

import com.sagar.watchnext.network.models.movies.Movie;
import com.sagar.watchnext.network.models.movies.moviedetail.MovieDetail;
import com.sagar.watchnext.network.models.movies.videos.Video;

import java.io.IOException;
import java.util.List;

/**
 * Created by SAGAR MAHOBIA on 06-Jul-18. at 16:17
 */
public interface MovieDetailActivityMvpContract {

    interface View {
        void onSucceedLoadingMovieDetail(MovieDetail movieDetail);

        void onErrorLoadingMovieDetail();
        void showToast(String msg);
    }

    interface Presenter {
        void onCreate(int movieId);

        void onDestroy();

    }

    interface Model {
        MovieDetail getMovieDetail(int movieId) throws IOException;

        List<Movie> getRecommendations(int movieId) throws IOException;

        List<Movie> getSimilars(int movieId) throws IOException;

        List<Video> getVideos(int movieId) throws IOException;

//todo re add
//        Credits getCredits(int movieId) throws IOException;

//        Images getImages(int movieId) throws IOException;//

//        ListOfBelonging getListOfBelonging(int movieId) throws IOException;//

//        Reviews getReviews(int movieId) throws IOException;
    }

}


