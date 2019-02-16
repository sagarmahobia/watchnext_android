package com.sagar.watchnext.activities.main.movies;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.main.MainActivity;
import com.sagar.watchnext.activities.moviedetail.MovieDetailActivity;
import com.sagar.watchnext.adapters.card.CardAdapter;
import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.adapters.listeners.EndlessRecyclerViewScrollListener;
import com.sagar.watchnext.databinding.FragmentMoviesBinding;
import com.sagar.watchnext.response.Response;
import com.sagar.watchnext.response.Status;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class MoviesFragment extends Fragment {

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

    private CardRecyclerModel inTheatersCardRecyclerModel;
    private CardRecyclerModel topRatedCardRecyclerModel;
    private CardRecyclerModel popularCardRecyclerModel;
    private CardRecyclerModel upcomingCardRecyclerModel;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesFragmentViewModel.class);

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

        binding.inTheaters.setModel(inTheatersCardRecyclerModel);
        binding.upcoming.setModel(upcomingCardRecyclerModel);
        binding.popular.setModel(popularCardRecyclerModel);
        binding.topRated.setModel(topRatedCardRecyclerModel);

        inTheatersCardRecyclerModel.setTitle("In Theaters");
        upcomingCardRecyclerModel.setTitle("Upcoming");
        popularCardRecyclerModel.setTitle("Popular");
        topRatedCardRecyclerModel.setTitle("Top Rated");

        List<LinearLayoutManager> linearLayoutManagers = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            linearLayoutManagers.add(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }

        binding.inTheaters.horizontalListRecycler.setLayoutManager(linearLayoutManagers.get(0));
        binding.upcoming.horizontalListRecycler.setLayoutManager(linearLayoutManagers.get(1));
        binding.popular.horizontalListRecycler.setLayoutManager(linearLayoutManagers.get(2));
        binding.topRated.horizontalListRecycler.setLayoutManager(linearLayoutManagers.get(3));

        EndlessRecyclerViewScrollListener scrollListenerForInTheaters =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) binding.inTheaters.horizontalListRecycler.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        viewModel.loadMoreInTheatersMovies(pageToLoad);
                        binding.inTheaters.progressBar.setVisibility(View.VISIBLE);
                    }
                };


        EndlessRecyclerViewScrollListener scrollListenerForUpcoming =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) binding.upcoming.horizontalListRecycler.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        viewModel.loadMoreUpcomingMovies(pageToLoad);
                        binding.upcoming.progressBar.setVisibility(View.VISIBLE);

                    }
                };

        EndlessRecyclerViewScrollListener scrollListenerForPopular =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) binding.popular.horizontalListRecycler.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        viewModel.loadMorePopularMovies(pageToLoad);
                        binding.popular.progressBar.setVisibility(View.VISIBLE);

                    }
                };

        EndlessRecyclerViewScrollListener scrollListenerForTopRated =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) binding.topRated.horizontalListRecycler.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        viewModel.loadMoreTopRatedMovies(pageToLoad);
                        binding.topRated.progressBar.setVisibility(View.VISIBLE);

                    }
                };

        binding.inTheaters.horizontalListRecycler.addOnScrollListener(scrollListenerForInTheaters);
        binding.upcoming.horizontalListRecycler.addOnScrollListener(scrollListenerForUpcoming);
        binding.popular.horizontalListRecycler.addOnScrollListener(scrollListenerForPopular);
        binding.topRated.horizontalListRecycler.addOnScrollListener(scrollListenerForTopRated);


        binding.inTheaters.horizontalListRecycler.setAdapter(inTheatersMoviesAdapter);
        binding.upcoming.horizontalListRecycler.setAdapter(upcomingMoviesAdapter);
        binding.popular.horizontalListRecycler.setAdapter(popularMoviesAdapter);
        binding.topRated.horizontalListRecycler.setAdapter(topRatedMoviesAdapter);


        inTheatersMoviesAdapter.setAdapterListener(model ->
                MoviesFragment.this.startMovieDetailActivity(model.getId()));

        popularMoviesAdapter.setAdapterListener(model ->
                MoviesFragment.this.startMovieDetailActivity(model.getId()));

        topRatedMoviesAdapter.setAdapterListener(model ->
                MoviesFragment.this.startMovieDetailActivity(model.getId()));

        upcomingMoviesAdapter.setAdapterListener(model ->
                MoviesFragment.this.startMovieDetailActivity(model.getId()));


        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.load();

            inTheatersCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            upcomingCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            popularCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            topRatedCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);

            scrollListenerForInTheaters.resetState();
            scrollListenerForUpcoming.resetState();
            scrollListenerForPopular.resetState();
            scrollListenerForTopRated.resetState();
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

        viewModel.getInTheatersMoviesLiveData().observe(this, this::onResponseInTheaters);
        viewModel.getUpcomingMoviesLiveData().observe(this, this::onResponseUpcoming);
        viewModel.getPopularMoviesLiveData().observe(this, this::onResponsePopular);
        viewModel.getTopRatedMoviesLiveData().observe(this, this::onResponseTopRated);

    }

    private void onResponseInTheaters(Response<List<CardModel>> response) {
        binding.swipeRefreshLayout.setRefreshing(false);
        if (response.getStatus() == Status.SUCCESS) {
            List<CardModel> data = response.getData();
            inTheatersMoviesAdapter.submitList(data);

            inTheatersCardRecyclerModel.setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            inTheatersCardRecyclerModel.setStatus(CardRecyclerModel.Status.ERROR);
        }
    }

    private void onResponseUpcoming(Response<List<CardModel>> response) {
        binding.swipeRefreshLayout.setRefreshing(false);
        if (response.getStatus() == Status.SUCCESS) {
            List<CardModel> data = response.getData();
            upcomingMoviesAdapter.submitList(data);

            upcomingCardRecyclerModel.setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            upcomingCardRecyclerModel.setStatus(CardRecyclerModel.Status.ERROR);
        }
    }

    private void onResponsePopular(Response<List<CardModel>> response) {
        binding.swipeRefreshLayout.setRefreshing(false);
        if (response.getStatus() == Status.SUCCESS) {
            List<CardModel> data = response.getData();
            popularMoviesAdapter.submitList(data);

            popularCardRecyclerModel.setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            popularCardRecyclerModel.setStatus(CardRecyclerModel.Status.ERROR);
        }
    }

    private void onResponseTopRated(Response<List<CardModel>> response) {
        binding.swipeRefreshLayout.setRefreshing(false);
        if (response.getStatus() == Status.SUCCESS) {
            List<CardModel> data = response.getData();
            topRatedMoviesAdapter.submitList(data);

            topRatedCardRecyclerModel.setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            topRatedCardRecyclerModel.setStatus(CardRecyclerModel.Status.ERROR);
        }
    }


    public void startMovieDetailActivity(int movieId) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("movie_id", movieId);
        startActivity(intent);
    }
}
