package com.sagar.watchnext.activities.search.movies;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.moviedetail.MovieDetailActivity;
import com.sagar.watchnext.activities.search.ActivityStateObserver;
import com.sagar.watchnext.activities.search.SearchActivity;
import com.sagar.watchnext.activities.search.movies.adapters.RecyclerAdapter;
import com.sagar.watchnext.adapters.listeners.EndlessRecyclerViewScrollListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieSearchFragment extends Fragment implements ActivityStateObserver, MovieSearchFragmentMvpContract.View {


    @Inject
    MovieSearchFragmentMvpContract.Presenter presenter;

    @Inject
    RecyclerAdapter adapter;

    @BindView(R.id.search_recycler_grid_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private String query = "";
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

    public MovieSearchFragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (query.isEmpty()) {
                hideProgress();
            } else {
                this.onQuery(query);
            }
        });

        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                presenter.onLoadMore(pageToLoad);
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SearchActivity activity = (SearchActivity) getActivity();
        if (activity != null) {
            activity.setActivityStateObserver(this);
            DaggerMovieSearchFragmentComponent.builder().
                    searchActivityComponent(activity.getComponent()).
                    movieSearchFragmentModule(new MovieSearchFragmentModule(this)).
                    build().
                    inject(this);
        }


        recyclerView.setAdapter(adapter);
        presenter.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onQuery(String query) {
        this.query = query;
        presenter.query(query);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onErrorLoadingShowList() {
        showToast("Couldn't load content.");
        Activity activity = getActivity();
        if (null != activity) {
            activity.finish();
        }
    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void resetEndlessLoader() {
        endlessRecyclerViewScrollListener.resetState();
    }

    @Override
    public void startMovieDetailActivity(int movieId) {

        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("movie_detail", movieId);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
