package com.sagar.watchnext.activities.main.home;

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
import com.sagar.watchnext.activities.main.MainActivityComponent;
import com.sagar.watchnext.activities.main.home.adapters.RecyclerAdapter;
import com.sagar.watchnext.activities.moviedetail.MovieDetailActivity;
import com.sagar.watchnext.activities.tvdetail.TvDetailActivity;
import com.sagar.watchnext.adapters.listeners.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class HomeFragment extends Fragment implements Contract.View {

    private RecyclerView recyclerViewOnTv;
    private RecyclerView recyclerViewInTheaters;

    private RelativeLayout inTheatersCard;
    private RelativeLayout onTvCard;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    RecyclerAdapter onTvRecyclerAdapter;

    @Inject
    RecyclerAdapter inTheatersRecyclerAdapter;

    @Inject
    Contract.Presenter presenter;


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {

        HomeFragment fragment = new HomeFragment();
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


        inTheatersCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);

        onTvCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);

        ((TextView) inTheatersCard.findViewById(R.id.card_header_text)).setText("In Theaters");
        ((TextView) onTvCard.findViewById(R.id.card_header_text)).setText("On TV");

        recyclerViewOnTv = onTvCard.findViewById(R.id.horizontal_list_recycler);
        recyclerViewInTheaters = inTheatersCard.findViewById(R.id.horizontal_list_recycler);

        List<LinearLayoutManager> linearLayoutManagers = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            linearLayoutManagers.add(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }

        recyclerViewOnTv.setLayoutManager(linearLayoutManagers.get(0));
        recyclerViewInTheaters.setLayoutManager(linearLayoutManagers.get(1));

        EndlessRecyclerViewScrollListener scrollListenerForOnTv =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) recyclerViewOnTv.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        presenter.loadMore(ListType.OnTv, pageToLoad);
                        onTvCard.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                    }
                };

        EndlessRecyclerViewScrollListener scrollListenerForInTheaters =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) recyclerViewInTheaters.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        presenter.loadMore(ListType.InTheaters, pageToLoad);
                        inTheatersCard.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

                    }
                };

        recyclerViewOnTv.addOnScrollListener(scrollListenerForOnTv);
        recyclerViewInTheaters.addOnScrollListener(scrollListenerForInTheaters);


        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.load();
            scrollListenerForOnTv.resetState();
            scrollListenerForInTheaters.resetState();
        });

        linearLayout.addView(inTheatersCard);
        linearLayout.addView(onTvCard);

        swipeRefreshLayout.setRefreshing(true);
        return swipeRefreshLayout;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("WatchNext - Home");
        }
        MainActivityComponent mainActivityComponent = ((MainActivity) getActivity()).getComponent();

        DaggerHomeFragmentComponent.builder().
                mainActivityComponent(mainActivityComponent)
                .homeFragmentModule(new HomeFragmentModule(this))
                .build().inject(this);

        getLifecycle().addObserver(presenter);

        onTvRecyclerAdapter.setListType(ListType.OnTv);
        inTheatersRecyclerAdapter.setListType(ListType.InTheaters);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onSucceedLoadingList(ListType listType) {
        switch (listType) {
            case InTheaters:
                recyclerViewInTheaters.setAdapter(inTheatersRecyclerAdapter);
                inTheatersCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                inTheatersCard.findViewById(R.id.error_text).setVisibility(View.GONE);
                break;
            default:
                recyclerViewOnTv.setAdapter(onTvRecyclerAdapter);
                onTvCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                onTvCard.findViewById(R.id.error_text).setVisibility(View.GONE);

        }
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onErrorLoadingList(ListType listType) {
        switch (listType) {
            case InTheaters:
                inTheatersCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
                inTheatersCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                break;
            default:
                onTvCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
                onTvCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void startMovieDetailActivity(int movieId) {

        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("movie_id", movieId);
        startActivity(intent);
    }

    @Override
    public void startTvDetailActivity(int tv_id) {

        Intent intent = new Intent(getContext(), TvDetailActivity.class);
        intent.putExtra("tv_id", tv_id);
        startActivity(intent);
    }

    @Override
    public void notifyAdaptersNewData(ListType listType) {
        switch (listType) {
            case InTheaters:
                inTheatersRecyclerAdapter.notifyDataSetChanged();
                inTheatersCard.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                break;
            case OnTv:
                onTvRecyclerAdapter.notifyDataSetChanged();
                onTvCard.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                break;
        }
    }
}
