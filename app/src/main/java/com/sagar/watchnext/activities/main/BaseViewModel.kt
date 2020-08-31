package com.sagar.watchnext.activities.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sagar.watchnext.adapters.card.CardModel
import com.sagar.watchnext.adapters.people.PeopleModel
import com.sagar.watchnext.network.models.people.Persons
import com.sagar.watchnext.network.newmodels.CardItem
import com.sagar.watchnext.network.newmodels.Result
import com.sagar.watchnext.network.repo.TMDBRepository
import com.sagar.watchnext.network.repo.TmdbTvRepo
import com.sagar.watchnext.response.Response
import com.sagar.watchnext.utils.ImageUrlUtil
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

open class BaseViewModel(private val tmdbRepository: TMDBRepository,
                         private val tvRepository: TmdbTvRepo) : ViewModel() {
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

    private val netflixShows: MutableList<CardModel> = ArrayList()
    private val appleShows: MutableList<CardModel> = ArrayList()
    private val amazonPrimeShows: MutableList<CardModel> = ArrayList()
    private val disneyPlusShows: MutableList<CardModel> = ArrayList()

    //PEOPLE
    private val popularPeople: MutableList<PeopleModel> = ArrayList();

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

    val netflixShowLiveData = MutableLiveData<Response<*>>()
    val appleShowsLivaData = MutableLiveData<Response<*>>()
    val amazonPrimeShowsLiveData = MutableLiveData<Response<*>>()
    val disneyPlusShowsLiveData = MutableLiveData<Response<*>>()


    //PEOPLE
    val popularPeopleLiveData = MutableLiveData<Response<*>>()

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

    val netflixShowsRecyclerModel = CardRecyclerModel()
    val appleShowsRecyclerModel = CardRecyclerModel()
    val amazonPrimeShowsRecyclerModel = CardRecyclerModel()
    val disneyPlusShowsRecyclerModel = CardRecyclerModel()


    //PEOPLE
    val peopleRecyclerModel = CardRecyclerModel()

    fun loadTrendingMovies() {
        disposables.add(tmdbRepository.getTrending("movie")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies: Result ->
                    trendingMovies.clear()
                    populateCardModels(trendingMovies, movies.cardItems)
                    trendingMoviesLiveData.setValue(Response.success<List<CardModel>>(trendingMovies))
                }
                        , { e: Throwable? -> upcomingMoviesLiveData.setValue(Response.error(e!!)) }))
    }

    fun loadInTheatersMovies() {
        disposables.add(tmdbRepository.getFirstPage("movie", "now_playing")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies: Result ->
                    inTheatersMovies.clear()
                    populateCardModels(inTheatersMovies, movies.cardItems)
                    inTheatersMoviesLiveData.setValue(Response.success<List<CardModel>>(inTheatersMovies))
                }
                        , { e: Throwable? -> inTheatersMoviesLiveData.setValue(Response.error(e!!)) }))
    }

    fun loadUpcomingMovies() {
        disposables.add(tmdbRepository.getFirstPage("movie", "upcoming")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies: Result ->
                    upcomingMovies.clear()
                    populateCardModels(upcomingMovies, movies.cardItems)
                    upcomingMoviesLiveData.setValue(Response.success<List<CardModel>>(upcomingMovies))
                }
                        , { e: Throwable? -> upcomingMoviesLiveData.setValue(Response.error(e!!)) }))
    }

    fun loadPopularMovies() {
        disposables.add(tmdbRepository.getFirstPage("movie", "popular")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies: Result ->
                    popularMovies.clear()
                    populateCardModels(popularMovies, movies.cardItems)
                    popularMoviesLiveData.setValue(Response.success<List<CardModel>>(popularMovies))
                }
                        , { e: Throwable? -> popularMoviesLiveData.setValue(Response.error(e!!)) }))
    }

    fun loadTopRatedMovies() {
        disposables.add(tmdbRepository.getFirstPage("movie", "top_rated")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies: Result ->
                    topRatedMovies.clear()
                    populateCardModels(topRatedMovies, movies.cardItems)
                    topRatedMoviesLiveData.setValue(Response.success<List<CardModel>>(topRatedMovies))
                }
                        , { e: Throwable? -> topRatedMoviesLiveData.setValue(Response.error(e!!)) }))
    }

    fun loadTrendingShows() {
        disposables.add(tmdbRepository.getTrending("tv")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    trendingShows.clear()
                    populateCardModels(trendingShows, shows.cardItems)
                    trendingShowsLiveData.setValue(Response.success<List<CardModel>>(trendingShows))
                }, { e: Throwable? -> trendingShowsLiveData.setValue(Response.error(e!!)) }))
    }

    fun loadAiringTodayShows() {
        disposables.add(tmdbRepository.getFirstPage("tv", "airing_today")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    airingTodayShows.clear()
                    populateCardModels(airingTodayShows, shows.cardItems)
                    airingTodayShowsLiveData.setValue(Response.success<List<CardModel>>(airingTodayShows))
                }, { e: Throwable? -> airingTodayShowsLiveData.setValue(Response.error(e!!)) }))
    }

    fun loadOnTheAirTodayShows() {
        disposables.add(tmdbRepository.getFirstPage("tv", "on_the_air")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    onTheAirShows.clear()
                    populateCardModels(onTheAirShows, shows.cardItems)
                    onTheAirShowsLiveData.setValue(Response.success<List<CardModel>>(onTheAirShows))
                }, { e: Throwable? -> onTheAirShowsLiveData.setValue(Response.error(e!!)) }))
    }

    fun loadPopularShows() {
        disposables.add(tmdbRepository.getFirstPage("tv", "popular")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    popularShows.clear()
                    populateCardModels(popularShows, shows.cardItems)
                    popularShowsLiveData.setValue(Response.success<List<CardModel>>(popularShows))
                }, { e: Throwable? -> popularShowsLiveData.setValue(Response.error(e!!)) }))
    }

    fun loadTopRatedShows() {
        disposables.add(tmdbRepository.getFirstPage("tv", "top_rated")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    topRatedShows.clear()
                    populateCardModels(topRatedShows, shows.cardItems)
                    topRatedShowsLiveData.setValue(Response.success<List<CardModel>>(topRatedShows))
                }, { e: Throwable? -> topRatedShowsLiveData.setValue(Response.error(e!!)) }))
    }

    fun loadPopularPeoples() {
        disposables.add(
                tmdbRepository.getPopularPeople()
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ people: Persons ->
                            popularPeople.clear()
                            populatePeopleModel(popularPeople, people)
                            popularPeopleLiveData.setValue(Response.success<List<PeopleModel>>(popularPeople))

                        }, { e: Throwable? -> popularPeopleLiveData.setValue(Response.error(e!!)) }))
    }

    fun loadNetflixShows() {
        disposables.add(tvRepository.getNetflixShows(1)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    netflixShows.clear()
                    populateCardModels(netflixShows, shows.cardItems)
                    netflixShowLiveData.setValue(Response.success<List<CardModel>>(netflixShows))
                }, { e: Throwable? -> netflixShowLiveData.setValue(Response.error(e!!)) }))
    }

    fun loadAppleTvShows() {
        disposables.add(tvRepository.getAppleTVShows(1)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    appleShows.clear()
                    populateCardModels(appleShows, shows.cardItems)
                    appleShowsLivaData.setValue(Response.success<List<CardModel>>(appleShows))
                }, { e: Throwable? -> appleShowsLivaData.setValue(Response.error(e!!)) }))
    }

    fun loadAmazonPrimeShows() {
        disposables.add(tvRepository.getAmazonShows(1)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    amazonPrimeShows.clear()
                    populateCardModels(amazonPrimeShows, shows.cardItems)
                    amazonPrimeShowsLiveData.setValue(Response.success<List<CardModel>>(amazonPrimeShows))
                }, { e: Throwable? -> amazonPrimeShowsLiveData.setValue(Response.error(e!!)) }))
    }

    fun loadDisneyPlusShows() {
        disposables.add(tvRepository.getDisneyPlusShows(1)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ shows: Result ->
                    disneyPlusShows.clear()
                    populateCardModels(disneyPlusShows, shows.cardItems)
                    disneyPlusShowsLiveData.setValue(Response.success<List<CardModel>>(disneyPlusShows))
                }, { e: Throwable? -> disneyPlusShowsLiveData.setValue(Response.error(e!!)) }))
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
            cardModel.rating = cardItem.voteAverage.toString()
            cardModels.add(cardModel)
        }
    }

    private fun populatePeopleModel(peopleModels: MutableList<PeopleModel>, persons: Persons) {
        if (persons.persons == null) {
            return
        }
        for (person in persons.persons) {
            var model = PeopleModel()
            model.title = person.name
            model.id = person.id
            model.image = ImageUrlUtil.getPosterImageUrl(person.profilePath)
            peopleModels.add(model)
        }

    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}