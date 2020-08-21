package com.sagar.watchnext.activities.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.main.BaseFragment;
import com.sagar.watchnext.activities.main.MainActivity;
import com.sagar.watchnext.activities.search.SearchActivity;
import com.sagar.watchnext.adapters.card.CardAdapter;
import com.sagar.watchnext.adapters.people.PeopleAdapter;
import com.sagar.watchnext.databinding.FragmentHomeBinding;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment {

    @Inject
    CardAdapter trendingMoviesAdapter;


    @Inject
    CardAdapter inTheatersMoviesAdapter;

    @Inject
    CardAdapter popularMoviesAdapter;

    @Inject
    CardAdapter topRatedMoviesAdapter;

    @Inject
    CardAdapter upcomingMoviesAdapter;


    @Inject
    CardAdapter trendingShowsAdapter;

    @Inject
    CardAdapter airingTodayAdapter;

    @Inject
    CardAdapter onTheAirAdapter;

    @Inject
    CardAdapter popularAdapter;

    @Inject
    CardAdapter topRatedAdapter;

    @Inject
    PeopleAdapter peopleAdapter;

    @Inject
    HomeFragmentViewModelFactory viewModelFactory;

    private FragmentHomeBinding binding;

    private HomeFragmentViewModel viewModel;

    private CardRecyclerModel trendingMoviesCardRecyclerModel;
    private CardRecyclerModel inTheatersCardRecyclerModel;
    private CardRecyclerModel topRatedCardRecyclerModel;
    private CardRecyclerModel popularCardRecyclerModel;
    private CardRecyclerModel upcomingCardRecyclerModel;

    private CardRecyclerModel trendingShowsRecyclerModel;
    private CardRecyclerModel airingTodayShowsRecyclerModel;
    private CardRecyclerModel onTheAirShowsRecyclerModel;
    private CardRecyclerModel popularShowsRecyclerModel;
    private CardRecyclerModel topRatedShowsRecyclerModel;

    private CardRecyclerModel peopleCardRecyclerModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(HomeFragmentViewModel.class);


        trendingMoviesCardRecyclerModel = viewModel.getTrendingMoviesCardRecyclerModel();
        inTheatersCardRecyclerModel = viewModel.getInTheatersCardRecyclerModel();
        topRatedCardRecyclerModel = viewModel.getTopRatedCardRecyclerModel();
        popularCardRecyclerModel = viewModel.getPopularCardRecyclerModel();
        upcomingCardRecyclerModel = viewModel.getUpcomingCardRecyclerModel();


        trendingShowsRecyclerModel = viewModel.getTrendingShowsRecyclerModel();
        airingTodayShowsRecyclerModel = viewModel.getAiringTodayShowsRecyclerModel();
        onTheAirShowsRecyclerModel = viewModel.getOnTheAirShowsRecyclerModel();
        popularShowsRecyclerModel = viewModel.getPopularShowsRecyclerModel();
        topRatedShowsRecyclerModel = viewModel.getTopRatedShowsRecyclerModel();

        peopleCardRecyclerModel = viewModel.getPeopleRecyclerModel();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        binding.trendingMovies.setModel(trendingMoviesCardRecyclerModel);
        binding.inTheatersMovies.setModel(inTheatersCardRecyclerModel);
        binding.upcomingMovies.setModel(upcomingCardRecyclerModel);
        binding.popularMovies.setModel(popularCardRecyclerModel);
        binding.topRatedMovies.setModel(topRatedCardRecyclerModel);

        binding.trendingShows.setModel(trendingShowsRecyclerModel);
        binding.airingTodayShows.setModel(airingTodayShowsRecyclerModel);
        binding.onTheAirShows.setModel(onTheAirShowsRecyclerModel);
        binding.popularShows.setModel(popularShowsRecyclerModel);
        binding.topRatedShows.setModel(topRatedShowsRecyclerModel);

        binding.popularPeople.setModel(peopleCardRecyclerModel);

        trendingMoviesCardRecyclerModel.setTitle("Trending");
        trendingMoviesCardRecyclerModel.setType("Movies");

        inTheatersCardRecyclerModel.setTitle("In Theaters");
        inTheatersCardRecyclerModel.setType("Movies");

        upcomingCardRecyclerModel.setTitle("Upcoming");
        upcomingCardRecyclerModel.setType("Movies");

        popularCardRecyclerModel.setTitle("Popular");
        popularCardRecyclerModel.setType("Movies");

        topRatedCardRecyclerModel.setTitle("Top Rated");
        topRatedCardRecyclerModel.setType("Movies");


        trendingShowsRecyclerModel.setTitle("Trending");
        trendingShowsRecyclerModel.setType("Shows");

        airingTodayShowsRecyclerModel.setTitle("Airing Today");
        airingTodayShowsRecyclerModel.setType("Shows");

        onTheAirShowsRecyclerModel.setTitle("On The Air");
        onTheAirShowsRecyclerModel.setType("Shows");

        popularShowsRecyclerModel.setTitle("Popular");
        popularShowsRecyclerModel.setType("Shows");

        topRatedShowsRecyclerModel.setTitle("Top Rated");
        topRatedShowsRecyclerModel.setType("Shows");

        peopleCardRecyclerModel.setTitle("Popular");
        peopleCardRecyclerModel.setType("People");

        binding.trendingMovies.seeAll.setOnClickListener(v -> {
            startList("movie", "trending", "Trending Movies");
        });
        binding.inTheatersMovies.seeAll.setOnClickListener(v -> {
            startList("movie", "now_playing", "Movies Now Playing");
        });
        binding.upcomingMovies.seeAll.setOnClickListener(v -> {
            startList("movie", "upcoming", "Upcoming Movies");
        });
        binding.popularMovies.seeAll.setOnClickListener(v -> {
            startList("movie", "popular", "Popular Movies");
        });
        binding.topRatedMovies.seeAll.setOnClickListener(v -> {
            startList("movie", "top_rated", "Top Rated Movies");
        });


        binding.trendingShows.seeAll.setOnClickListener(v -> {
            startList("tv", "trending", "Trending Shows");
        });
        binding.airingTodayShows.seeAll.setOnClickListener(v -> {
            startList("tv", "airing_today", "Shows Airing Today");
        });
        binding.onTheAirShows.seeAll.setOnClickListener(v -> {
            startList("tv", "on_the_air", "On The Air Shows");
        });
        binding.popularShows.seeAll.setOnClickListener(v -> {
            startList("tv", "popular", "Popular Shows");
        });
        binding.topRatedShows.seeAll.setOnClickListener(v -> {
            startList("tv", "top_rated", "Top Rated Shows");
        });

        binding.popularPeople.seeAll.setOnClickListener(v -> {
            //TODO
            Toast.makeText(getContext(), "Coming Soon.", Toast.LENGTH_SHORT).show();
        });

        binding.trendingMovies.horizontalListRecycler.setAdapter(trendingMoviesAdapter);
        binding.inTheatersMovies.horizontalListRecycler.setAdapter(inTheatersMoviesAdapter);
        binding.upcomingMovies.horizontalListRecycler.setAdapter(upcomingMoviesAdapter);
        binding.popularMovies.horizontalListRecycler.setAdapter(popularMoviesAdapter);
        binding.topRatedMovies.horizontalListRecycler.setAdapter(topRatedMoviesAdapter);

        binding.trendingShows.horizontalListRecycler.setAdapter(trendingShowsAdapter);
        binding.airingTodayShows.horizontalListRecycler.setAdapter(airingTodayAdapter);
        binding.onTheAirShows.horizontalListRecycler.setAdapter(onTheAirAdapter);
        binding.popularShows.horizontalListRecycler.setAdapter(popularAdapter);
        binding.topRatedShows.horizontalListRecycler.setAdapter(topRatedAdapter);

        binding.popularPeople.horizontalListRecycler.setAdapter(peopleAdapter);

        binding.searchBar.setOnClickListener(v -> {
            Intent intent = new Intent(this.getContext(), SearchActivity.class);
            startActivity(intent);
        });


        trendingMoviesAdapter.setAdapterListener(model -> this.startMovieDetailActivity(model.getId()));
        inTheatersMoviesAdapter.setAdapterListener(model -> this.startMovieDetailActivity(model.getId()));
        upcomingMoviesAdapter.setAdapterListener(model -> this.startMovieDetailActivity(model.getId()));
        popularMoviesAdapter.setAdapterListener(model -> this.startMovieDetailActivity(model.getId()));
        topRatedMoviesAdapter.setAdapterListener(model -> this.startMovieDetailActivity(model.getId()));

        trendingShowsAdapter.setAdapterListener(model -> this.startTvDetailActivity(model.getId()));
        airingTodayAdapter.setAdapterListener(model -> this.startTvDetailActivity(model.getId()));
        onTheAirAdapter.setAdapterListener(model -> this.startTvDetailActivity(model.getId()));
        popularAdapter.setAdapterListener(model -> this.startTvDetailActivity(model.getId()));
        topRatedAdapter.setAdapterListener(model -> this.startTvDetailActivity(model.getId()));

        peopleAdapter.setClickListener(peopleModel -> {
            Toast.makeText(getContext(), "Coming Soon.", Toast.LENGTH_SHORT).show();
        });

        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.load();

            trendingMoviesCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            inTheatersCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            upcomingCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            popularCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            topRatedCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);

            trendingShowsRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            airingTodayShowsRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            onTheAirShowsRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            popularShowsRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            topRatedShowsRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);

            peopleCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
        });

        binding.swipeRefreshLayout.setRefreshing(true);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("WatchNext - Home");
        }

        viewModel.getTrendingMoviesLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, trendingMoviesAdapter, trendingMoviesCardRecyclerModel));
        viewModel.getInTheatersMoviesLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, inTheatersMoviesAdapter, inTheatersCardRecyclerModel));
        viewModel.getUpcomingMoviesLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, upcomingMoviesAdapter, upcomingCardRecyclerModel));
        viewModel.getPopularMoviesLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, popularMoviesAdapter, popularCardRecyclerModel));
        viewModel.getTopRatedMoviesLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, topRatedMoviesAdapter, topRatedCardRecyclerModel));


        viewModel.getTrendingShowsLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, trendingShowsAdapter, trendingShowsRecyclerModel));
        viewModel.getAiringTodayShowsLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, airingTodayAdapter, airingTodayShowsRecyclerModel));
        viewModel.getOnTheAirShowsLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, onTheAirAdapter, onTheAirShowsRecyclerModel));
        viewModel.getPopularShowsLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, popularAdapter, popularShowsRecyclerModel));
        viewModel.getTopRatedShowsLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, topRatedAdapter, topRatedShowsRecyclerModel));

        viewModel.getPopularPeopleLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, peopleAdapter, peopleCardRecyclerModel));
    }

    @Override
    protected void stopSwipeRefresh() {
        binding.swipeRefreshLayout.setRefreshing(false);
    }
}
