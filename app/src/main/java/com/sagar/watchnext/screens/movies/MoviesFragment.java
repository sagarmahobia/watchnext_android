package com.sagar.watchnext.screens.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sagar.watchnext.R;
import com.sagar.watchnext.screens.MainActivity;
import com.sagar.watchnext.screens.MainActivityComponent;
import com.sagar.watchnext.screens.moviedetailactivity.MovieDetailActivity;
import com.sagar.watchnext.screens.movies.adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MoviesFragment extends Fragment implements MoviesFragmentMvpContract.View {

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
    MoviesFragmentMvpContract.Presenter presenter;

    public MoviesFragment() {
        // Required empty public constructor
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
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString("");
//        }

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        swipeRefreshLayout = (SwipeRefreshLayout) inflater.inflate(R.layout.fragment_base, container, false);
        LinearLayout linearLayout = swipeRefreshLayout.findViewById(R.id.card_list_container);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.onCreate());

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

        inTheatersMoviesCard.findViewById(R.id.see_all_button).setOnClickListener(v -> showToast("Feature N/A"));
        upcomingMoviesCard.findViewById(R.id.see_all_button).setOnClickListener(v -> showToast("Feature N/A"));
        popularMoviesCard.findViewById(R.id.see_all_button).setOnClickListener(v -> showToast("Feature N/A"));
        topRatedMoviesCard.findViewById(R.id.see_all_button).setOnClickListener(v -> showToast("Feature N/A"));


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

        linearLayout.addView(inTheatersMoviesCard);
        linearLayout.addView(upcomingMoviesCard);
        linearLayout.addView(popularMoviesCard);
        linearLayout.addView(topRatedMoviesCard);


        return swipeRefreshLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();


        if (actionBar != null) {
            actionBar.setTitle("Movies");
        }
        MainActivityComponent mainActivityComponent = ((MainActivity) getActivity()).getComponent();

        DaggerMoviesFragmentComponent.builder()
                .mainActivityComponent(mainActivityComponent)
                .moviesFragmentModule(new MoviesFragmentModule(this))
                .build().inject(this);

        inTheatersMoviesAdapter.setListType(ListType.InTheaters);
        upcomingMoviesAdapter.setListType(ListType.Upcoming);
        popularMoviesAdapter.setListType(ListType.Popular);
        topRatedMoviesAdapter.setListType(ListType.TopRated);

        presenter.onCreate();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
        Log.d("Lifecycle", "On destroy view called");
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

}
