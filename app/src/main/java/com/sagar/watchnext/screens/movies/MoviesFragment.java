package com.sagar.watchnext.screens.movies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.sagar.watchnext.R;
import com.sagar.watchnext.screens.MainActivity;
import com.sagar.watchnext.screens.MainActivityComponent;
import com.sagar.watchnext.screens.movies.adapters.InTheatersMoviesAdapter;
import com.sagar.watchnext.screens.movies.adapters.PopularMoviesAdapter;
import com.sagar.watchnext.screens.movies.adapters.TopRatedMoviesAdapter;
import com.sagar.watchnext.screens.movies.adapters.UpcomingMoviesAdapter;

import javax.inject.Inject;


public class MoviesFragment extends Fragment implements MoviesFragmentMvpContract.View {

    RelativeLayout inTheatersMoviesCard;
    RelativeLayout upcomingMoviesCard;
    RelativeLayout popularMoviesCard;
    RelativeLayout topRatedMoviesCard;

    @Inject
    InTheatersMoviesAdapter inTheatersMoviesAdapter;

    @Inject
    PopularMoviesAdapter popularMoviesAdapter;

    @Inject
    TopRatedMoviesAdapter topRatedMoviesAdapter;

    @Inject
    UpcomingMoviesAdapter upcomingMoviesAdapter;

    @Inject
    MoviesFragmentMvpContract.Presenter presenter;

    RecyclerView recyclerViewInTheaters;
    RecyclerView recyclerViewUpcoming;
    RecyclerView recyclerViewPopular;
    RecyclerView recyclerViewTopRated;

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


        ScrollView scrollView = (ScrollView) inflater.inflate(R.layout.fragment_movies, container, false);
        LinearLayout linearLayout = scrollView.findViewById(R.id.card_list_container);


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

        LinearLayoutManager horizontalLayoutManagerForInTheaters
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManagerForUpcoming
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManagerForPopular
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManagerForTopRated
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewInTheaters.setLayoutManager(horizontalLayoutManagerForInTheaters);
        recyclerViewUpcoming.setLayoutManager(horizontalLayoutManagerForUpcoming);
        recyclerViewPopular.setLayoutManager(horizontalLayoutManagerForPopular);
        recyclerViewTopRated.setLayoutManager(horizontalLayoutManagerForTopRated);

        linearLayout.addView(inTheatersMoviesCard);
        linearLayout.addView(upcomingMoviesCard);
        linearLayout.addView(popularMoviesCard);
        linearLayout.addView(topRatedMoviesCard);


        return scrollView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivityComponent mainActivityComponent = ((MainActivity) getActivity()).getComponent();

        DaggerMoviesFragmentComponent.builder()
                .mainActivityComponent(mainActivityComponent)
                .moviesFragmentModule(new MoviesFragmentModule(this))
                .build().inject(this);

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
    public void onSucceedLoadingInTheatersMovieList() {

        recyclerViewInTheaters.setAdapter(inTheatersMoviesAdapter);
        inTheatersMoviesCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoadingInTheatersMovieList() {
        inTheatersMoviesCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
        inTheatersMoviesCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onSucceedLoadingUpcomingMovieList() {
        recyclerViewUpcoming.setAdapter(upcomingMoviesAdapter);
        upcomingMoviesCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoadingUpcomingMovieList() {
        upcomingMoviesCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
        upcomingMoviesCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onSucceedLoadingPopularMovieList() {
        recyclerViewPopular.setAdapter(popularMoviesAdapter);
        popularMoviesCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoadingPopularMovieList() {
        popularMoviesCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
        popularMoviesCard.findViewById(R.id.progressBar).setVisibility(View.GONE);

    }

    @Override
    public void onSucceedLoadingTopRatedMovieList() {
        recyclerViewTopRated.setAdapter(topRatedMoviesAdapter);
        topRatedMoviesCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoadingTopRatedMovieList() {
        topRatedMoviesCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
        topRatedMoviesCard.findViewById(R.id.progressBar).setVisibility(View.GONE);

    }

}
