package com.sagar.watchnext.activities.main.tv;

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
import com.sagar.watchnext.databinding.FragmentTvBinding;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import javax.inject.Inject;


public class TvFragment extends BaseFragment {


    @Inject
    CardAdapter airingTodayAdapter;

    @Inject
    CardAdapter onTheAirAdapter;

    @Inject
    CardAdapter popularAdapter;

    @Inject
    CardAdapter topRatedAdapter;

    @Inject
    TvFragmentViewModelFactory viewModelFactory;

    private FragmentTvBinding binding;

    private TvFragmentViewModel viewModel;

    private CardRecyclerModel airingTodayShowsRecyclerModel;
    private CardRecyclerModel onTheAirShowsRecyclerModel;
    private CardRecyclerModel popularShowsRecyclerModel;
    private CardRecyclerModel topRatedShowsRecyclerModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(TvFragmentViewModel.class);

        airingTodayShowsRecyclerModel = viewModel.getAiringTodayShowsRecyclerModel();
        onTheAirShowsRecyclerModel = viewModel.getOnTheAirShowsRecyclerModel();
        popularShowsRecyclerModel = viewModel.getPopularShowsRecyclerModel();
        topRatedShowsRecyclerModel = viewModel.getTopRatedShowsRecyclerModel();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv, container, false);

        binding.airingToday.setModel(airingTodayShowsRecyclerModel);
        binding.onTheAir.setModel(onTheAirShowsRecyclerModel);
        binding.popular.setModel(popularShowsRecyclerModel);
        binding.topRated.setModel(topRatedShowsRecyclerModel);

        airingTodayShowsRecyclerModel.setTitle("Airing Today");
        onTheAirShowsRecyclerModel.setTitle("On The Air");
        popularShowsRecyclerModel.setTitle("Popular");
        topRatedShowsRecyclerModel.setTitle("Top Rated");

        binding.airingToday.seeAll.setOnClickListener(v -> {
            startList("tv", "airing_today","Shows Airing Today");
        });
        binding.onTheAir.seeAll.setOnClickListener(v -> {
            startList("tv", "on_the_air","Shows On The Air");
        });
        ;
        binding.popular.seeAll.setOnClickListener(v -> {
            startList("tv", "popular","Popular Shows");
        });
        ;
        binding.topRated.seeAll.setOnClickListener(v -> {
            startList("tv", "top_rated","Top Rated Shows");
        });


        binding.airingToday.horizontalListRecycler.setAdapter(airingTodayAdapter);
        binding.onTheAir.horizontalListRecycler.setAdapter(onTheAirAdapter);
        binding.popular.horizontalListRecycler.setAdapter(popularAdapter);
        binding.topRated.horizontalListRecycler.setAdapter(topRatedAdapter);


        airingTodayAdapter.setAdapterListener((model) -> this.startTvDetailActivity(model.getId()));
        onTheAirAdapter.setAdapterListener((model) -> this.startTvDetailActivity(model.getId()));
        popularAdapter.setAdapterListener((model) -> this.startTvDetailActivity(model.getId()));
        topRatedAdapter.setAdapterListener((model) -> this.startTvDetailActivity(model.getId()));


        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.load();
            airingTodayShowsRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            onTheAirShowsRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            popularShowsRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);
            topRatedShowsRecyclerModel.setStatus(CardRecyclerModel.Status.LOADING);

        });

        binding.swipeRefreshLayout.setRefreshing(true);

        return binding.getRoot();
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("TV Shows");
        }


        viewModel.getAiringTodayShowsLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, airingTodayAdapter, airingTodayShowsRecyclerModel));
        viewModel.getOnTheAirShowsLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, onTheAirAdapter, onTheAirShowsRecyclerModel));
        viewModel.getPopularShowsLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, popularAdapter, popularShowsRecyclerModel));
        viewModel.getTopRatedShowsLiveData().observe(this.getViewLifecycleOwner(),
                response -> onResponse(response, topRatedAdapter, topRatedShowsRecyclerModel));

    }

    @Override
    protected void stopSwipeRefresh() {
        binding.swipeRefreshLayout.setRefreshing(false);
    }


}
