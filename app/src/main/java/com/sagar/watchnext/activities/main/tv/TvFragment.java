package com.sagar.watchnext.activities.main.tv;

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

import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.list.ListActivity;
import com.sagar.watchnext.activities.main.MainActivity;
import com.sagar.watchnext.activities.tvdetail.TvDetailActivity;
import com.sagar.watchnext.adapters.card.CardAdapter;
import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.databinding.FragmentTvBinding;
import com.sagar.watchnext.response.Response;
import com.sagar.watchnext.response.Status;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class TvFragment extends Fragment {


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
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TvFragmentViewModel.class);

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

        List<LinearLayoutManager> linearLayoutManagers = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            linearLayoutManagers.add(new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false));
        }

        binding.airingToday.horizontalListRecycler.setLayoutManager(linearLayoutManagers.get(0));
        binding.onTheAir.horizontalListRecycler.setLayoutManager(linearLayoutManagers.get(1));
        binding.popular.horizontalListRecycler.setLayoutManager(linearLayoutManagers.get(2));
        binding.topRated.horizontalListRecycler.setLayoutManager(linearLayoutManagers.get(3));

        binding.airingToday.horizontalListRecycler.setAdapter(airingTodayAdapter);
        binding.onTheAir.horizontalListRecycler.setAdapter(onTheAirAdapter);
        binding.popular.horizontalListRecycler.setAdapter(popularAdapter);
        binding.topRated.horizontalListRecycler.setAdapter(topRatedAdapter);

        binding.airingToday.seeAll.setOnClickListener(v -> {
            startList("tv", "airing_today");
        });
        binding.onTheAir.seeAll.setOnClickListener(v -> {
            startList("tv", "on_the_air");
        });
        ;
        binding.popular.seeAll.setOnClickListener(v -> {
            startList("tv", "popular");
        });
        ;
        binding.topRated.seeAll.setOnClickListener(v -> {
            startList("tv", "top_rated");
        });

        airingTodayAdapter.setAdapterListener((model) -> TvFragment.this.startTvDetailActivity(model.getId()));
        onTheAirAdapter.setAdapterListener((model) -> TvFragment.this.startTvDetailActivity(model.getId()));
        popularAdapter.setAdapterListener((model) -> TvFragment.this.startTvDetailActivity(model.getId()));
        topRatedAdapter.setAdapterListener((model) -> TvFragment.this.startTvDetailActivity(model.getId()));


        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.load();

        });

        binding.swipeRefreshLayout.setRefreshing(true);

        return binding.getRoot();
    }

    private void startList(String type, String subtype) {

        Intent intent = new Intent(this.getContext(), ListActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("subtype", subtype);
        startActivity(intent);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("TV Shows");
        }

        viewModel.getAiringTodayShowsLiveData().observe(this, this::onResponseAiringToday);
        viewModel.getOnTheAirShowsLiveData().observe(this, this::onResponseOnTheAir);
        viewModel.getPopularShowsLiveData().observe(this, this::onResponsePopular);
        viewModel.getTopRatedShowsLiveData().observe(this, this::onResponseTopRated);
    }

    private void onResponseAiringToday(Response<List<CardModel>> response) {
        binding.swipeRefreshLayout.setRefreshing(false);
        if (response.getStatus() == Status.SUCCESS) {
            List<CardModel> data = response.getData();
            airingTodayAdapter.submitList(data);

            airingTodayShowsRecyclerModel.setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            airingTodayShowsRecyclerModel.setStatus(CardRecyclerModel.Status.ERROR);
        }
    }

    private void onResponseOnTheAir(Response<List<CardModel>> response) {
        binding.swipeRefreshLayout.setRefreshing(false);
        if (response.getStatus() == Status.SUCCESS) {
            List<CardModel> data = response.getData();
            onTheAirAdapter.submitList(data);

            onTheAirShowsRecyclerModel.setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            onTheAirShowsRecyclerModel.setStatus(CardRecyclerModel.Status.ERROR);
        }
    }

    private void onResponsePopular(Response<List<CardModel>> response) {
        binding.swipeRefreshLayout.setRefreshing(false);
        if (response.getStatus() == Status.SUCCESS) {
            List<CardModel> data = response.getData();
            popularAdapter.submitList(data);

            popularShowsRecyclerModel.setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            popularShowsRecyclerModel.setStatus(CardRecyclerModel.Status.ERROR);
        }
    }

    private void onResponseTopRated(Response<List<CardModel>> response) {
        binding.swipeRefreshLayout.setRefreshing(false);
        if (response.getStatus() == Status.SUCCESS) {
            List<CardModel> data = response.getData();
            topRatedAdapter.submitList(data);

            topRatedShowsRecyclerModel.setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            topRatedShowsRecyclerModel.setStatus(CardRecyclerModel.Status.ERROR);
        }
    }

    public void startTvDetailActivity(int tv_id) {
        Intent intent = new Intent(getContext(), TvDetailActivity.class);
        intent.putExtra("tv_id", tv_id);
        startActivity(intent);
    }

}
