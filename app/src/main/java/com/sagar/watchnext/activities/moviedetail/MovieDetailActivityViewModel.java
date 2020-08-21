package com.sagar.watchnext.activities.moviedetail;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.adapters.video.VideoModel;
import com.sagar.watchnext.network.models.movies.moviedetail.Genre;
import com.sagar.watchnext.network.models.movies.moviedetail.ProductionCompany;
import com.sagar.watchnext.network.models.tv.videos.Video;
import com.sagar.watchnext.network.newmodels.CardItem;
import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.observablemodels.ContentVisibilityModel;
import com.sagar.watchnext.observablemodels.HeaderModel;
import com.sagar.watchnext.response.Response;
import com.sagar.watchnext.utils.ImageUrlUtil;
import com.sagar.watchnext.viewmodels.DisposableViewModel;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailActivityViewModel extends DisposableViewModel {

    private TMDBRepository tmdbRepository;

    private MovieDetailActivityModel activityModel = new MovieDetailActivityModel();
    private MovieDetailActivityDataModel dataModel = new MovieDetailActivityDataModel();
    private HeaderModel headerModel = new HeaderModel();
    private ContentVisibilityModel visibilityModel = new ContentVisibilityModel();

    private CardRecyclerModel recommendationCardRecyclerModel = new CardRecyclerModel();
    private CardRecyclerModel similarCardRecyclerModel = new CardRecyclerModel();
    private CardRecyclerModel videoCardRecycleModel = new CardRecyclerModel();


    private MutableLiveData<Response> recommendationResponse = new MutableLiveData<>();
    private MutableLiveData<Response> similarResponse = new MutableLiveData<>();
    private MutableLiveData<Response> videosResponse = new MutableLiveData<>();

    public MovieDetailActivityViewModel(TMDBRepository tmdbRepository) {
        super();
        this.tmdbRepository = tmdbRepository;
    }

    MovieDetailActivityModel getActivityModel() {
        return activityModel;
    }

    MovieDetailActivityDataModel getDataModel() {
        return dataModel;
    }

    HeaderModel getHeaderModel() {
        return headerModel;
    }

    public ContentVisibilityModel getVisibilityModel() {
        return visibilityModel;
    }

    MutableLiveData<Response> getRecommendationResponse() {
        return recommendationResponse;
    }

    public MutableLiveData<Response> getSimilarResponse() {
        return similarResponse;
    }

    CardRecyclerModel getRecommendationCardRecyclerModel() {
        return recommendationCardRecyclerModel;
    }

    public MutableLiveData<Response> getVideosResponse() {
        return videosResponse;
    }

    public CardRecyclerModel getSimilarCardRecyclerModel() {
        return similarCardRecyclerModel;
    }


    public CardRecyclerModel getVideoCardRecycleModel() {
        return videoCardRecycleModel;
    }

    @SuppressLint("DefaultLocale")
    void load() {
        visibilityModel.setStatus(ContentVisibilityModel.Status.LOADING);
        disposable.add(tmdbRepository.getMovieDetails(dataModel.getMovieId()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movieDetail -> {
                            //getSupportActionBar().setTitle(movieDetail.getTitle());
                            headerModel.setBackDropImageUrl(ImageUrlUtil.getBackdropImageUrl(movieDetail.getBackdropPath()));//todo
                            headerModel.setPosterImageUrl(ImageUrlUtil.getPosterImageUrl(movieDetail.getPosterPath()));

                            headerModel.setTitle(movieDetail.getTitle());

                            String releaseDate = movieDetail.getReleaseDate();

                            if (releaseDate != null && releaseDate.length() > 4) {
                                headerModel.setYear(releaseDate.substring(0, 4));
                            } else {
                                headerModel.setYear("N/A");
                            }


                            int t = movieDetail.getRuntime();
                            int hours = t / 60; //since both are ints, you get an int
                            int minutes = t % 60;

                            if (hours == 0 && minutes == 0) {
                                headerModel.setRunTime("");
                            } else if (hours == 0) {
                                headerModel.setRunTime(String.format("%02dm", minutes));
                            } else if (minutes == 0) {
                                headerModel.setRunTime(String.format("%02dh", hours));
                            } else {
                                headerModel.setRunTime(String.format("%02dh %02dm", hours, minutes));
                            }

                            StringBuilder genreString = new StringBuilder();
                            List<Genre> genres = movieDetail.getGenres();
                            int index = 1;
                            int size = genres.size();
                            for (Genre genre : movieDetail.getGenres()) {
                                if (index++ < size) {
                                    genreString.append(genre.getName()).append(", ");
                                } else {
                                    genreString.append(genre.getName());
                                }
                            }

                            if (size > 0) {
                                headerModel.setGenres(genreString.toString());
                            } else {
                                headerModel.setGenres("N/A");
                            }

                            String overview = movieDetail.getOverview();
                            if (overview != null) {
                                activityModel.setOverViewText(overview);
                            } else {
                                activityModel.setOverViewText("N/A");
                            }

                            String originalTitle = movieDetail.getOriginalTitle();
                            if (originalTitle != null) {
                                activityModel.setOriginalTitle(originalTitle);
                            } else {
                                activityModel.setOriginalTitle("N/A");
                            }

                            String originalLanguage = movieDetail.getOriginalLanguage();
                            if (originalLanguage != null) {
                                Locale loc = new Locale(originalLanguage);
                                originalLanguage = loc.getDisplayLanguage(loc);
                                activityModel.setOriginalLanguage(originalLanguage);
                            } else {
                                activityModel.setOriginalLanguage("N/A");
                            }

                            String status = movieDetail.getStatus();
                            if (status != null) {
                                activityModel.setStatusText(status);
                            } else {
                                activityModel.setStatusText("N/A");
                            }

                            if (releaseDate != null) {
                                activityModel.setReleaseDate(releaseDate);
                            } else {
                                activityModel.setReleaseDate("N/A");

                            }

                            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
                            format.setMaximumFractionDigits(0);

                            long budget = movieDetail.getBudget();
                            if (budget != 0) {
                                activityModel.setBudget(format.format(budget));
                            } else {
                                activityModel.setBudget("N/A");
                            }

                            long revenue = movieDetail.getRevenue();
                            if (revenue != 0) {
                                activityModel.setRevenue(format.format(revenue));
                            } else {
                                activityModel.setRevenue("N/A");
                            }

                            StringBuilder productionCompanyString = new StringBuilder();
                            index = 1;
                            size = movieDetail.getProductionCompanies().size();
                            for (ProductionCompany productionCompany : movieDetail.getProductionCompanies()) {
                                if (index++ < size) {
                                    productionCompanyString.append(productionCompany.getName()).append(", ");
                                } else {
                                    productionCompanyString.append(productionCompany.getName());
                                }
                            }
                            if (size > 0) {
                                activityModel.setProductionCompanies(productionCompanyString.toString());
                            } else {
                                activityModel.setProductionCompanies("N/A");
                            }

                            String homepage = movieDetail.getHomepage();
                            if (homepage != null && homepage.length() > 0) {
                                activityModel.setHomepage(homepage);
                            } else {
                                activityModel.setHomepage("N/A");
                            }
                            visibilityModel.setStatus(ContentVisibilityModel.Status.LOADED);

                        }, error -> {
                            visibilityModel.setStatus(ContentVisibilityModel.Status.ERROR);
                        }
                )
        );

        /*   */
        recommendationCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);

        disposable.add(tmdbRepository.getListWithId("movie", dataModel.getMovieId(), "recommendations", 1)
                .map(result -> populateCardModels(result.getCardItems()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {

                    recommendationResponse.setValue(Response.success(result));

                }, throwable -> {
                    recommendationResponse.setValue(Response.error(throwable));

                }));
        /*   */
        similarCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);

        disposable.add(tmdbRepository.getListWithId("movie", dataModel.getMovieId(), "similar", 1)
                .map(result -> populateCardModels(result.getCardItems()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {

                    similarResponse.setValue(Response.success(result));

                }, throwable -> {
                    similarResponse.setValue(Response.error(throwable));

                }));


        videoCardRecycleModel.setStatus(CardRecyclerModel.Status.LOADING);
        disposable.add(tmdbRepository.getVideos("movie", dataModel.getMovieId())
                .map(result -> {

                    List<VideoModel> videoModels = new ArrayList<>();


                    for (Video video : result.getVideos()) {
                        VideoModel videoModel = new VideoModel();


                        String site = video.getSite();
                        if (site == null || !site.equalsIgnoreCase("YouTube")) {
                            continue;
                        }

                        String image = "https://img.youtube.com/vi/" + video.getKey() + "/mqdefault.jpg";
                        String url = "https://www.youtube.com/watch?v=" + video.getKey();

                        videoModel.setImage(image);
                        videoModel.setUrl(url);
                        videoModel.setId(video.getKey());
                        videoModel.setType(video.getType());
                        videoModel.setTitle(video.getName());

                        videoModels.add(videoModel);


                    }

                    return videoModels;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        videos -> videosResponse.setValue(Response.success(videos)),
                        throwable -> videosResponse.setValue(Response.error(throwable))
                )
        );
    }

    private List<CardModel> populateCardModels(List<CardItem> cardItems) {
        List<CardModel> cardModels = new ArrayList<>();
        for (CardItem cardItem : cardItems) {
            CardModel cardModel = new CardModel();
            cardModel.setId(cardItem.getId());
            cardModel.setImageUrl(ImageUrlUtil.getPosterImageUrl(cardItem.getPosterPath()));
            cardModel.setTitle(cardItem.getTitle());
            cardModel.setRating(String.valueOf(cardItem.getVoteAverage()));
            cardModels.add(cardModel);
        }
        return cardModels;
    }

}
