package com.sagar.watchnext.activities.tvdetail;


import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.network.models.tv.details.Genre;
import com.sagar.watchnext.network.models.tv.details.Network;
import com.sagar.watchnext.network.models.tv.details.ProductionCompany;
import com.sagar.watchnext.network.newmodels.CardItem;
import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.observablemodels.ContentVisibilityModel;
import com.sagar.watchnext.observablemodels.HeaderModel;
import com.sagar.watchnext.response.Response;
import com.sagar.watchnext.utils.ImageUrlUtil;
import com.sagar.watchnext.viewmodels.DisposableViewModel;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TvDetailActivityViewModel extends DisposableViewModel {

    private TMDBRepository tmdbRepository;


    private TvDetailActivityDataModel dataModel = new TvDetailActivityDataModel();
    private HeaderModel headerModel = new HeaderModel();
    private TvDetailActivityModel activityModel = new TvDetailActivityModel();
    private ContentVisibilityModel visibilityModel = new ContentVisibilityModel();

    private CardRecyclerModel recommendedCardRecyclerModel = new CardRecyclerModel();
    private CardRecyclerModel similarCardRecyclerModel = new CardRecyclerModel();
    private MutableLiveData<Response> recommendationResponse = new MutableLiveData<>();
    private MutableLiveData<Response> similarResponse = new MutableLiveData<>();

    public TvDetailActivityViewModel(TMDBRepository tmdbRepository) {

        this.tmdbRepository = tmdbRepository;
    }

    public TvDetailActivityDataModel getDataModel() {
        return dataModel;
    }

    public HeaderModel getHeaderModel() {
        return headerModel;
    }

    public TvDetailActivityModel getActivityModel() {
        return activityModel;
    }

    public ContentVisibilityModel getVisibilityModel() {
        return visibilityModel;
    }

    public MutableLiveData<Response> getRecommendationResponse() {
        return recommendationResponse;
    }

    public MutableLiveData<Response> getSimilarResponse() {
        return similarResponse;
    }

    public CardRecyclerModel getRecommendedCardRecyclerModel() {
        return recommendedCardRecyclerModel;
    }

    public CardRecyclerModel getSimilarCardRecyclerModel() {
        return similarCardRecyclerModel;
    }

    @SuppressLint("DefaultLocale")
    public void load() {
        visibilityModel.setStatus(ContentVisibilityModel.Status.LOADING);
        disposable.add(tmdbRepository.getTVDetails(dataModel.getTvId()).
                        subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(
                                details -> {

                                    headerModel.setBackDropImageUrl(ImageUrlUtil.getBackdropImageUrl(details.getBackdropPath()));
                                    headerModel.setPosterImageUrl(ImageUrlUtil.getPosterImageUrl(details.getPosterPath()));

                                    headerModel.setTitle(details.getName());
                                    String firstAirDate = details.getFirstAirDate();
                                    if (firstAirDate != null && firstAirDate.length() >= 4) {
                                        headerModel.setYear(firstAirDate.substring(0, 4));
                                    }

                                    List<Integer> episodeRunTimeList = details.getEpisodeRunTimeList();
                                    if (episodeRunTimeList != null && !episodeRunTimeList.isEmpty()) {
                                        int t = episodeRunTimeList.get(0);
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
                                    }
                                    StringBuilder genreString = new StringBuilder();
                                    List<Genre> genres = details.getGenres();
                                    int index = 1;
                                    int size = genres.size();
                                    for (Genre genre : details.getGenres()) {
                                        if (index++ < size) {
                                            genreString.append(genre.getName()).append(", ");
                                        } else {
                                            genreString.append(genre.getName());
                                        }
                                    }

                                    if (size > 0) {
                                        headerModel.setGenres(genreString.toString());
                                    } else {
                                        headerModel.setGenres("");
                                    }

                                    String overview = details.getOverview();
                                    if (overview != null) {
                                        activityModel.setOverViewText(overview);
                                    } else {
                                        activityModel.setOverViewText("N/A");
                                    }

                                    String originalName = details.getOriginalName();
                                    if (originalName != null) {
                                        activityModel.setOriginalTitle(originalName);
                                    } else {
                                        activityModel.setOriginalTitle("N/A");
                                    }

                                    String originalLanguage = details.getOriginalLanguage();
                                    if (originalLanguage != null) {
                                        Locale loc = new Locale(originalLanguage);
                                        originalLanguage = loc.getDisplayLanguage(loc);
                                        activityModel.setOriginalLanguage(originalLanguage);
                                    } else {
                                        activityModel.setOriginalLanguage("N/A");
                                    }

                                    String showType = details.getType();
                                    if (showType != null) {
                                        activityModel.setShowType(showType);
                                    } else {
                                        activityModel.setShowType("N/A");
                                    }

                                    String showStatus = details.getStatus();
                                    if (showStatus != null) {
                                        activityModel.setStatusText(showStatus);
                                    } else {
                                        activityModel.setStatusText("N/A");
                                    }

                                    if (firstAirDate != null) {
                                        activityModel.setFirstAirDate(firstAirDate);
                                    } else {
                                        activityModel.setFirstAirDate("N/A");

                                    }

                                    String lastAirDate = details.getLastAirDate();
                                    if (lastAirDate != null) {
                                        activityModel.setLastAirDate(lastAirDate);
                                    } else {
                                        activityModel.setLastAirDate("N/A");
                                    }

                                    StringBuilder networkStringBuilder = new StringBuilder();
                                    index = 1;
                                    size = details.getNetworks().size();
                                    for (Network network : details.getNetworks()) {
                                        if (index++ < size) {
                                            networkStringBuilder.append(network.getName()).append(", ");
                                        } else {
                                            networkStringBuilder.append(network.getName());
                                        }
                                    }
                                    if (size > 0) {
                                        activityModel.setNetwork(networkStringBuilder.toString());
                                    } else {
                                        activityModel.setNetwork("N/A");
                                    }

                                    StringBuilder productionCompanyString = new StringBuilder();
                                    index = 1;
                                    size = details.getProductionCompanies().size();
                                    for (ProductionCompany productionCompany : details.getProductionCompanies()) {
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

                                    String homepage = details.getHomepage();
                                    activityModel.setHomepage(homepage);

                                    visibilityModel.setStatus(ContentVisibilityModel.Status.LOADED);
//                            if (homepage != null && homepage.length() > 0) {
//                                SpannableString spannableString = new SpannableString(homepage);
//                                ClickableSpan clickableSpan = new ClickableSpan() {
//                                    @Override
//                                    public void onClick(@NonNull View view) {
//                                        Intent i = new Intent(Intent.ACTION_VIEW);
//                                        i.setData(Uri.parse(homepage));
//                                        startActivity(i);
//                                    }
//                                };
//                                spannableString.setSpan(clickableSpan, 0, homepage.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//                                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, homepage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                                binding.homepageText.setText(spannableString);
//                                binding.homepageText.setMovementMethod(LinkMovementMethod.getInstance());
//                            }

                                },
                                error -> {
                                    visibilityModel.setStatus(ContentVisibilityModel.Status.ERROR);

                                })
        );
        recommendedCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);

        disposable.add(tmdbRepository.getListWithId("tv", dataModel.getTvId(), "recommendations", 1)
                .map(result -> populateCardModels(result.getCardItems()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {

                    recommendationResponse.setValue(Response.success(result));

                }, throwable -> {
                    recommendationResponse.setValue(Response.error(throwable));

                }));

        similarCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);

        disposable.add(tmdbRepository.getListWithId("tv", dataModel.getTvId(), "similar", 1)
                .map(result -> populateCardModels(result.getCardItems()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {

                    similarResponse.setValue(Response.success(result));

                }, throwable -> {
                    similarResponse.setValue(Response.error(throwable));

                }));
    }


    private List<CardModel> populateCardModels(List<CardItem> cardItems) {
        List<CardModel> cardModels = new ArrayList<>();
        for (CardItem cardItem : cardItems) {
            CardModel cardModel = new CardModel();
            cardModel.setId(cardItem.getId());
            cardModel.setImageUrl(ImageUrlUtil.getPosterImageUrl(cardItem.getPosterPath()));
            cardModel.setTitle(cardItem.getTitle());
            cardModels.add(cardModel);
        }
        return cardModels;
    }


}
