package com.sagar.watchnext.activities.main.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.main.MainActivity;
import com.sagar.watchnext.activities.main.movies.adapters.RecyclerAdapter;
import com.sagar.watchnext.activities.moviedetail.MovieDetailActivity;
import com.sagar.watchnext.adapters.listeners.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class MoviesFragment extends Fragment implements Contract.View {

    private RelativeLayout inTheatersMoviesCard;
    private RelativeLayout upcomingMoviesCard;
    private RelativeLayout popularMoviesCard;
    private RelativeLayout topRatedMoviesCard;

    private RecyclerView recyclerViewInTheaters;
    private RecyclerView recyclerViewUpcoming;
    private RecyclerView recyclerViewPopular;
    private RecyclerView recyclerViewTopRated;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    RecyclerAdapter inTheatersMoviesAdapter;

    @Inject
    RecyclerAdapter popularMoviesAdapter;

    @Inject
    RecyclerAdapter topRatedMoviesAdapter;

    @Inject
    RecyclerAdapter upcomingMoviesAdapter;

    @Inject
    Contract.Presenter presenter;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }


    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        swipeRefreshLayout = (SwipeRefreshLayout) inflater.inflate(R.layout.fragment_base, container, false);
        LinearLayout linearLayout = swipeRefreshLayout.findViewById(R.id.card_list_container);


        inTheatersMoviesCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);

        upcomingMoviesCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);

        popularMoviesCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);

        topRatedMoviesCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);


        ((TextView) inTheatersMoviesCard.findViewById(R.id.card_header_text)).setText("In Theaters");
        ((TextView) upcomingMoviesCard.findViewById(R.id.card_header_text)).setText("Upcoming");
        ((TextView) popularMoviesCard.findViewById(R.id.card_header_text)).setText("Popular");
        ((TextView) topRatedMoviesCard.findViewById(R.id.card_header_text)).setText("Top Rated");


        recyclerViewInTheaters = inTheatersMoviesCard.findViewById(R.id.horizontal_list_recycler);
        recyclerViewUpcoming = upcomingMoviesCard.findViewById(R.id.horizontal_list_recycler);
        recyclerViewPopular = popularMoviesCard.findViewById(R.id.horizontal_list_recycler);
        recyclerViewTopRated = topRatedMoviesCard.findViewById(R.id.horizontal_list_recycler);

        List<LinearLayoutManager> linearLayoutManagers = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {

            linearLayoutManagers.add(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
        recyclerViewInTheaters.setLayoutManager(linearLayoutManagers.get(0));
        recyclerViewUpcoming.setLayoutManager(linearLayoutManagers.get(1));
        recyclerViewPopular.setLayoutManager(linearLayoutManagers.get(2));
        recyclerViewTopRated.setLayoutManager(linearLayoutManagers.get(3));


        EndlessRecyclerViewScrollListener scrollListenerForInTheaters =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) recyclerViewInTheaters.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        inTheatersMoviesCard.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                        presenter.loadMore(ListType.InTheaters, pageToLoad);
                    }
                };


        EndlessRecyclerViewScrollListener scrollListenerForUpcoming =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) recyclerViewUpcoming.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        upcomingMoviesCard.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                        presenter.loadMore(ListType.Upcoming, pageToLoad);

                    }
                };

        EndlessRecyclerViewScrollListener scrollListenerForPopular =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) recyclerViewPopular.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        popularMoviesCard.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                        presenter.loadMore(ListType.Popular, pageToLoad);

                    }
                };

        EndlessRecyclerViewScrollListener scrollListenerForTopRated =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) recyclerViewTopRated.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        topRatedMoviesCard.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                        presenter.loadMore(ListType.TopRated, pageToLoad);

                    }
                };

        recyclerViewInTheaters.addOnScrollListener(scrollListenerForInTheaters);
        recyclerViewUpcoming.addOnScrollListener(scrollListenerForUpcoming);
        recyclerViewPopular.addOnScrollListener(scrollListenerForPopular);
        recyclerViewTopRated.addOnScrollListener(scrollListenerForTopRated);


        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.load();
            scrollListenerForInTheaters.resetState();
            scrollListenerForUpcoming.resetState();
            scrollListenerForPopular.resetState();
            scrollListenerForTopRated.resetState();
        });


        linearLayout.addView(inTheatersMoviesCard);
        linearLayout.addView(upcomingMoviesCard);
        linearLayout.addView(popularMoviesCard);
        linearLayout.addView(topRatedMoviesCard);

        swipeRefreshLayout.setRefreshing(true);
        return swipeRefreshLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();


        if (actionBar != null) {
            actionBar.setTitle("Movies");
        }

        getLifecycle().addObserver(presenter);

        inTheatersMoviesAdapter.setListType(ListType.InTheaters);
        upcomingMoviesAdapter.setListType(ListType.Upcoming);
        popularMoviesAdapter.setListType(ListType.Popular);
        topRatedMoviesAdapter.setListType(ListType.TopRated);

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSucceedLoadingMovieList(ListType listType) {
        swipeRefreshLayout.setRefreshing(false);
        switch (listType) {
            case InTheaters:
                recyclerViewInTheaters.setAdapter(inTheatersMoviesAdapter);
                inTheatersMoviesCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                inTheatersMoviesCard.findViewById(R.id.error_text).setVisibility(View.GONE);
                break;
            case Upcoming:
                recyclerViewUpcoming.setAdapter(upcomingMoviesAdapter);
                upcomingMoviesCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                upcomingMoviesCard.findViewById(R.id.error_text).setVisibility(View.GONE);
                break;
            case Popular:
                recyclerViewPopular.setAdapter(popularMoviesAdapter);
                popularMoviesCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                popularMoviesCard.findViewById(R.id.error_text).setVisibility(View.GONE);
                break;
            default://top rated
                recyclerViewTopRated.setAdapter(topRatedMoviesAdapter);
                topRatedMoviesCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                topRatedMoviesCard.findViewById(R.id.error_text).setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onErrorLoadingMovieList(ListType listType) {
        swipeRefreshLayout.setRefreshing(false);

        switch (listType) {
            case InTheaters:
                inTheatersMoviesCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
                inTheatersMoviesCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                break;
            case Upcoming:
                upcomingMoviesCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
                upcomingMoviesCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                break;
            case Popular:
                popularMoviesCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
                popularMoviesCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                break;
            default://top rated
                topRatedMoviesCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
                topRatedMoviesCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void startMovieDetailActivity(int movieId) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("movie_id", movieId);
        startActivity(intent);
    }

    @Override
    public void notifyAdaptersNewData(ListType listType) {
        switch (listType) {
            case InTheaters:
                inTheatersMoviesAdapter.notifyDataSetChanged();
                inTheatersMoviesCard.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                break;
            case Upcoming:
                upcomingMoviesAdapter.notifyDataSetChanged();
                upcomingMoviesCard.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                break;
            case Popular:
                popularMoviesAdapter.notifyDataSetChanged();
                popularMoviesCard.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                break;
            case TopRated:
                topRatedMoviesAdapter.notifyDataSetChanged();
                topRatedMoviesCard.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                break;
        }
    }

}
