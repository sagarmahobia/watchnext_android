package com.sagar.watchnext.activities.main.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.main.MainActivity;
import com.sagar.watchnext.activities.moviedetail.MovieDetailActivity;
import com.sagar.watchnext.activities.tvdetail.TvDetailActivity;
import com.sagar.watchnext.adapters.card.CardAdapter;
import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.adapters.listeners.EndlessRecyclerViewScrollListener;
import com.sagar.watchnext.databinding.FragmentHomeBinding;
import com.sagar.watchnext.response.Response;
import com.sagar.watchnext.response.Status;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class HomeFragment extends Fragment {

    @Inject
    CardAdapter tvShowsRecyclerAdapter;

    @Inject
    CardAdapter moviesRecyclerAdapter;

    @Inject
    HomeFragmentViewModelFactory viewModelFactory;

    private FragmentHomeBinding binding;

    private HomeFragmentViewModel viewModel;

    private CardRecyclerModel moviesCardRecyclerModel;
    private CardRecyclerModel tvShowsCardRecyclerModel;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeFragmentViewModel.class);

        moviesCardRecyclerModel = viewModel.getMoviesCardRecyclerModel();
        tvShowsCardRecyclerModel = viewModel.getTvShowsCardRecyclerModel();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        binding.movies.setModel(moviesCardRecyclerModel);
        binding.tvShows.setModel(tvShowsCardRecyclerModel);

        moviesCardRecyclerModel.setTitle("In Theaters");
        tvShowsCardRecyclerModel.setTitle("On TV");

        binding.movies.horizontalListRecycler.setLayoutManager(
                new LinearLayoutManager(
                        this.getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false)
        );

        binding.tvShows.horizontalListRecycler.setLayoutManager(
                new LinearLayoutManager(
                        this.getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false)
        );

        EndlessRecyclerViewScrollListener scrollListenerForInTheaters =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) binding.movies.horizontalListRecycler.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        viewModel.loadMoreMovies(pageToLoad);
                        binding.movies.progressBar.setVisibility(View.VISIBLE);

                    }
                };

        EndlessRecyclerViewScrollListener scrollListenerForOnTv =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) binding.tvShows.horizontalListRecycler.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        viewModel.loadMoreTvShows(pageToLoad);
                        binding.tvShows.progressBar.setVisibility(View.VISIBLE);
                    }
                };

        binding.movies.horizontalListRecycler.addOnScrollListener(scrollListenerForInTheaters);
        binding.tvShows.horizontalListRecycler.addOnScrollListener(scrollListenerForOnTv);

        binding.movies.horizontalListRecycler.setAdapter(moviesRecyclerAdapter);
        binding.tvShows.horizontalListRecycler.setAdapter(tvShowsRecyclerAdapter);

        moviesRecyclerAdapter.setAdapterListener(model ->
                HomeFragment.this.startMovieDetailActivity(model.getId()));

        tvShowsRecyclerAdapter.setAdapterListener(model ->
                HomeFragment.this.startTvDetailActivity(model.getId()));

        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.load();

            moviesCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            tvShowsCardRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);

            scrollListenerForOnTv.resetState();
            scrollListenerForInTheaters.resetState();
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

        viewModel.getMoviesLiveData().observe(this, this::onResponseMovies);
        viewModel.getShowsLiveData().observe(this, this::onResponseTvShows);

    }

    private void onResponseMovies(Response<List<CardModel>> response) {
        binding.swipeRefreshLayout.setRefreshing(false);

        if (response.getStatus() == Status.SUCCESS) {
            List<CardModel> data = response.getData();
            moviesRecyclerAdapter.submitList(data);

            moviesCardRecyclerModel.setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            moviesCardRecyclerModel.setStatus(CardRecyclerModel.Status.ERROR);
        }
    }

    private void onResponseTvShows(Response<List<CardModel>> response) {
        binding.swipeRefreshLayout.setRefreshing(false);

        if (response.getStatus() == Status.SUCCESS) {

            List<CardModel> data = response.getData();
            tvShowsRecyclerAdapter.submitList(data);
            tvShowsCardRecyclerModel.setStatus(CardRecyclerModel.Status.SUCCESS);

        } else if (response.getStatus() == Status.ERROR) {
            tvShowsCardRecyclerModel.setStatus(CardRecyclerModel.Status.ERROR);
        }
    }

    //    @Override
    public void startMovieDetailActivity(int movieId) {

        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("movie_id", movieId);
        startActivity(intent);
    }

    //    @Override
    public void startTvDetailActivity(int tv_id) {

        Intent intent = new Intent(getContext(), TvDetailActivity.class);
        intent.putExtra("tv_id", tv_id);
        startActivity(intent);
    }

}
