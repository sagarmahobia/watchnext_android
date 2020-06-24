package com.sagar.watchnext.activities.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sagar.watchnext.adapters.card.CardModel
import com.sagar.watchnext.network.newmodels.CardItem
import com.sagar.watchnext.network.newmodels.Result
import com.sagar.watchnext.network.repo.TMDBRepository
import com.sagar.watchnext.response.Response
import com.sagar.watchnext.utils.ImageUrlUtil
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

open class BaseViewModel(private val tmdbRepository: TMDBRepository) : ViewModel() {
    private val disposables = CompositeDisposable()

    //MOVIES
    private val trendingMovies: MutableList<CardModel> = ArrayList()
    private val inTheatersMovies: MutableList<CardModel> = ArrayList()
    private val topRatedMovies: MutableList<CardModel> = ArrayList()
    private val popularMovies: MutableList<CardModel> = ArrayList()
    private val upcomingMovies: MutableList<CardModel> = ArrayList()

    //TV
    private val trendingShows: MutableList<CardModel> = ArrayList()
    private val airingTodayShows: MutableList<CardModel> = ArrayList()
    private val onTheAirShows: MutableList<CardModel> = ArrayList()
    private val popularShows: MutableList<CardModel> = ArrayList()
    private val topRatedShows: MutableList<CardModel> = ArrayList()

    //MOVIES
    val trendingMoviesLiveData = MutableLiveData<Response<*>>()
    val inTheatersMoviesLiveData = MutableLiveData<Response<*>>()
    val topRatedMoviesLiveData = MutableLiveData<Response<*>>()
    val popularMoviesLiveData = MutableLiveData<Response<*>>()
    val upcomingMoviesLiveData = MutableLiveData<Response<*>>()

    //TV
    val trendingShowsLiveData = MutableLiveData<Response<*>>()
    val airingTodayShowsLiveData = MutableLiveData<Response<*>>()
    val onTheAirShowsLiveData = MutableLiveData<Response<*>>()
    val popularShowsLiveData = MutableLiveData<Response<*>>()
    val topRatedShowsLiveData = MutableLiveData<Response<*>>()

    //MOVIES
    val trendingMoviesCardRecyclerModel = CardRecyclerModel()
    val inTheatersCardRecyclerModel = CardRecyclerModel()
    val topRatedCardRecyclerModel = CardRecyclerModel()
    val popularCardRecyclerModel = CardRecyclerModel()
    val upcomingCardRecyclerModel = CardRecyclerModel()

    //TV
    val trendingShowsRecyclerModel = CardRecyclerModel()
    val airingTodayShowsRecyclerModel = CardRecyclerModel()
    val onTheAirShowsRecyclerModel = CardRecyclerModel()
    val popularShowsRecyclerModel = CardRecyclerModel()
    val topRatedShowsRecyclerModel = CardRecyclerModel()

    fun loadTrendingMovies() {
        disposables.add(tmdbRepository.getTrending("movie")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies: Result ->
                    trendingMovies.clear()
                    populateCardModels(trendingMovies, movies.cardItems)
                    trendingMoviesLiveData.setValue(Response.success<List<CardModel>>(trendingMovies))
                }
                ) { e: Throwable? -> upcomingMoviesLiveData.setValue(Response.error(e!!)) })
    }

    fun loadInTheatersMovies() {
        disposables.add(tmdbRepository.getFirstPage("movie", "now_playing")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies: Result ->
                    inTheatersMovies.clear()
                    populateCardModels(inTheatersMovies, movies.cardItems)
                    inTheatersMoviesLiveData.setValue(Response.success<List<CardModel>>(inTheatersMovies))
                }
                ) { e: Throwable? -> inTheatersMoviesLiveData.setValue(Response.error(e!!)) })
    }

    fun loadUpcomingMovies() {
        disposables.add(tmdbRepository.getFirstPage("movie", "upcoming")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies: Result ->
                    upcomingMovies.clear()
                    populateCardModels(upcomingMovies, movies.cardItems)
                    upcomingMoviesLiveData.setValue(Response.success<List<CardModel>>(upcomingMovies))
                }
                ) { e: Throwable? -> upcomingMoviesLiveData.setValue(Response.error(e!!)) })
    }

    fun loadPopularMovies() {
        disposables.add(tmdbRepository.getFirstPage("movie", "popular")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies: Result ->
                    popularMovies.clear()
                    populateCardModels(popularMovies, movies.cardItems)
                    popularMoviesLiveData.setValue(Response.success<List<CardModel>>(popularMovies))
                }
                ) { e: Throwable? -> popularMoviesLiveData.setValue(Response.error(e!!)) })
    }

    fun loadTopRatedMovies() {
        disposables.add(tmdbRepository.getFirstPage("movie", "top_rated")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies: Result ->
                    topRatedMovies.clear()
                    populateCardModels(topRatedMovies, movies.cardItems)
                    topRatedMoviesLiveData.setValue(Response.success<List<CardModel>>(topRatedMovies))
                }
                ) { e: Throwable? -> topRatedMoviesLiveData.setValue(Response.error(e!!)) })
    }

    fun loadTrendingShows() {
        disposables.add(tmdbRepository.getTrending("tv")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    trendingShows.clear()
                    populateCardModels(trendingShows, shows.cardItems)
                    trendingShowsLiveData.setValue(Response.success<List<CardModel>>(trendingShows))
                }) { e: Throwable? -> trendingShowsLiveData.setValue(Response.error(e!!)) })
    }

    fun loadAiringTodayShows() {
        disposables.add(tmdbRepository.getFirstPage("tv", "airing_today")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    airingTodayShows.clear()
                    populateCardModels(airingTodayShows, shows.cardItems)
                    airingTodayShowsLiveData.setValue(Response.success<List<CardModel>>(airingTodayShows))
                }) { e: Throwable? -> airingTodayShowsLiveData.setValue(Response.error(e!!)) })
    }

    fun loadOnTheAirTodayShows() {
        disposables.add(tmdbRepository.getFirstPage("tv", "on_the_air")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    onTheAirShows.clear()
                    populateCardModels(onTheAirShows, shows.cardItems)
                    onTheAirShowsLiveData.setValue(Response.success<List<CardModel>>(onTheAirShows))
                }) { e: Throwable? -> onTheAirShowsLiveData.setValue(Response.error(e!!)) })
    }

    fun loadPopularShows() {
        disposables.add(tmdbRepository.getFirstPage("tv", "popular")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    popularShows.clear()
                    populateCardModels(popularShows, shows.cardItems)
                    popularShowsLiveData.setValue(Response.success<List<CardModel>>(popularShows))
                }) { e: Throwable? -> popularShowsLiveData.setValue(Response.error(e!!)) })
    }

    fun loadTopRatedShows() {
        disposables.add(tmdbRepository.getFirstPage("tv", "top_rated")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    topRatedShows.clear()
                    populateCardModels(topRatedShows, shows.cardItems)
                    topRatedShowsLiveData.setValue(Response.success<List<CardModel>>(topRatedShows))
                }) { e: Throwable? -> topRatedShowsLiveData.setValue(Response.error(e!!)) })
    }

    private fun populateCardModels(cardModels: MutableList<CardModel>, cardItems: List<CardItem>?) {
        if (cardItems == null) {
            return
        }
        for (cardItem in cardItems) {
            val cardModel = CardModel()
            cardModel.id = cardItem.id
            cardModel.imageUrl = ImageUrlUtil.getPosterImageUrl(cardItem.posterPath)
            cardModel.title = cardItem.title
            cardModel.rating =   cardItem.voteAverage.toString()
            cardModels.add(cardModel)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}