package com.sagar.watchnext.activities.main.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.main.BaseFragment;
import com.sagar.watchnext.activities.main.MainActivity;
import com.sagar.watchnext.adapters.card.CardAdapter;
import com.sagar.watchnext.databinding.FragmentMoviesBinding;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import javax.inject.Inject;


public class MoviesFragment extends BaseFragment {

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
    MoviesFragmentViewModelFactory viewModelFactory;

    private FragmentMoviesBinding binding;

    private MoviesFragmentViewModel viewModel;

    private CardRecyclerModel trendingCardRecyclerModel;
    private CardRecyclerModel inTheatersCardRecyclerModel;
    private CardRecyclerModel topRatedCardRecyclerModel;
    private CardRecyclerModel popularCardRecyclerModel;
    private CardRecyclerModel upcomingCardRecyclerModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MoviesFragmentViewModel.class);

        trendingCardRecyclerModel = viewModel.getTrendingMoviesCardRecyclerModel();
        inTheatersCardRecyclerModel = viewModel.getInTheatersCardRecyclerModel();
        topRatedCardRecyclerModel = viewModel.getTopRatedCardRecyclerModel();
        popularCardRecyclerModel = viewModel.getPopularCardRecyclerModel();
        upcomingCardRecyclerModel = viewModel.getUpcomingCardRecyclerModel();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);

        binding.trendingMovies.setModel(trendingCardRecyclerModel);
        binding.inTheaters.setModel(inTheatersCardRecyclerModel);
        binding.upcoming.setModel(upcomingCardRecyclerModel);
        binding.popular.setModel(popularCardRecyclerModel);
        binding.topRated.setModel(topRatedCardRecyclerModel);

        trendingCardRecyclerModel.setTitle("Trending");
        inTheatersCardRecyclerModel.setTitle("In Theaters");
        upcomingCardRecyclerModel.setTitle("Upcoming");
        popularCardRecyclerModel.setTitle("Popular");
        topRatedCardRecyclerModel.setTitle("Top Rated");

        binding.trendingMovies.seeAll.setOnClickListener(v -> {
            startList("movie", "trending", "Trending Movies");
        });

        binding.inTheaters.seeAll.setOnClickListener(v -> {
            startList("movie", "now_playing", "Now Playing Movies");
        });
        binding.upcoming.seeAll.setOnClickListener(v -> {
            startList("movie", "upcoming", "Upcoming Movies");
        });
        binding.popular.seeAll.setOnClickListener(v -> {
            startList("movie", "popular", "Popular Movies");
        });
        binding.topRated.seeAll.setOnClickListener(v -> {
            startList("movie", "top_rated", "Top Rated Movies");
        });

        binding.trendingMovies.horizontalListRecycler.setAdapter(trendingMoviesAdapter);
        binding.inTheaters.horizontalListRecycler.setAdapter(inTheatersMoviesAdapter);
        binding.upcoming.horizontalListRecycler.setAdapter(upcomingMoviesAdapter);
        binding.popular.horizontalListRecycler.setAdapter(popularMoviesAdapter);
        binding.topRated.horizontalListRecycler.setAdapter(topRatedMoviesAdapter);


        trendingMoviesAdapter.setAdapterListener(model -> this.startMovieDetailActivity(model.getId()));
        inTheatersMoviesAdapter.setAdapterListener(model -> this.startMovieDetailActivity(model.getId()));
        popularMoviesAdapter.setAdapterListener(model -> this.startMovieDetailActivity(model.getId()));
        topRatedMoviesAdapter.setAdapterListener(model -> this.startMovieDetailActivity(model.getId()));
        upcomingMoviesAdapter.setAdapterListener(model -> this.startMovieDetailActivity(model.getId()));


        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.load();

            trendingCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            inTheatersCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            upcomingCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            popularCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            topRatedCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);

        });

        binding.swipeRefreshLayout.setRefreshing(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();


        if (actionBar != null) {
            actionBar.setTitle("Movies");
        }


        viewModel.getTrendingMoviesLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, trendingMoviesAdapter, trendingCardRecyclerModel));

        viewModel.getInTheatersMoviesLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, inTheatersMoviesAdapter, inTheatersCardRecyclerModel));

        viewModel.getUpcomingMoviesLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, upcomingMoviesAdapter, upcomingCardRecyclerModel));

        viewModel.getPopularMoviesLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, popularMoviesAdapter, popularCardRecyclerModel));

        viewModel.getTopRatedMoviesLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, topRatedMoviesAdapter, topRatedCardRecyclerModel));

    }

    @Override
    protected void stopSwipeRefresh() {
        binding.swipeRefreshLayout.setRefreshing(false);
    }
}
