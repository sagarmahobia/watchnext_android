package com.sagar.watchnext.activities.list;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.list.showadapter.ShowAdapter;
import com.sagar.watchnext.activities.moviedetail.MovieDetailActivity;
import com.sagar.watchnext.activities.tvdetail.TvDetailActivity;
import com.sagar.watchnext.databinding.ActivityListBinding;
import com.sagar.watchnext.response.PagingState;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ListActivity extends AppCompatActivity implements ListActivityHandler {

    @Inject
    ListActivityViewModelFactory viewModelFactory;

    @Inject
    ShowAdapter adapter;

    private ActivityListBinding binding;

    private ListActivityViewModel viewModel;

    private ListActivityModel activityModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        String type = getIntent().getStringExtra("type");
        String subtype = getIntent().getStringExtra("subtype");
        int id = getIntent().getIntExtra("id", -1);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListActivityViewModel.class);
        activityModel = viewModel.getActivityModel();
        binding.setModel(activityModel);
        binding.setHandler(this);

        viewModel.prepare(type, subtype,id);

        binding.recycler.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recycler.setAdapter(adapter);
        viewModel.getPagedListStateLiveData().observe(this, pagingState -> {
            if (pagingState.getState() == PagingState.Loading) {
                // binding.swipeRefresh.setRefreshing(true);
                // binding.noFeeds.setVisibility(View.GONE);
                //todo
            } else if (pagingState.getState() == PagingState.Success) {

                // binding.swipeRefresh.setRefreshing(false);
                // binding.recycler.setVisibility(View.VISIBLE);
                //todo

            } else if (pagingState.getState() == PagingState.Error) {
//                binding.swipeRefresh.setRefreshing(false);
                //todo
            }
        });

        viewModel.getPagedListLiveData().observe(this, showModels -> {
            adapter.submitList(showModels);
            if (showModels.isEmpty()) {
                // binding.recycler.setVisibility(View.GONE);
                // binding.noFeeds.setVisibility(View.VISIBLE);

            } else {
                //  binding.recycler.setVisibility(View.VISIBLE);
                //  binding.noFeeds.setVisibility(View.GONE);

            }
        });


        adapter.setClickListener(showModel -> {

            if (type.equalsIgnoreCase("tv")) {

                Intent intent = new Intent(this, TvDetailActivity.class);
                intent.putExtra("tv_id", showModel.getId());
                startActivity(intent);
            } else if (type.equalsIgnoreCase("movie")) {
                Intent intent = new Intent(this, MovieDetailActivity.class);
                intent.putExtra("movie_id", showModel.getId());
                startActivity(intent);
            } else {
            }

        });
    }
}
