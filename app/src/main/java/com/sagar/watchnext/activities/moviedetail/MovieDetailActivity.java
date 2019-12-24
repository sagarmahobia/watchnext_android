package com.sagar.watchnext.activities.moviedetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.sagar.watchnext.R;
import com.sagar.watchnext.databinding.ActivityMovieDetailBinding;
import com.sagar.watchnext.observablemodels.ContentVisibilityModel;
import com.sagar.watchnext.observablemodels.HeaderModel;
import com.sagar.watchnext.utils.PixelDensityUtil;
import com.sagar.watchnext.viewmodelfactories.ApplicationViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MovieDetailActivity extends AppCompatActivity {

    @Inject
    PixelDensityUtil pixelDensityUtil;

    @Inject
    ApplicationViewModelFactory viewModelFactory;

    private MovieDetailActivityViewModel viewModel;

    private MovieDetailActivityDataModel dataModel;

    private MovieDetailActivityModel activityModel;
    private ContentVisibilityModel visibilityModel;
    private HeaderModel headerModel;

    private int movie_id;

    private ActivityMovieDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);


        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        pixelDensityUtil.getBackDropImageHeight()
                );
        binding.header.backdropImage.setLayoutParams(lp);

        Intent intent = getIntent();
        movie_id = intent.getIntExtra("movie_id", -1);
        if (movie_id == -1) {
            //todo handle error in getting movie_id
            this.finish();
            return;
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailActivityViewModel.class);

        dataModel = viewModel.getDataModel();
        dataModel.setMovieId(movie_id);
        activityModel = viewModel.getActivityModel();
        headerModel = viewModel.getHeaderModel();
        visibilityModel = viewModel.getVisibilityModel();

        binding.setModel(activityModel);
        binding.setVisibilityModel(visibilityModel);
        binding.header.setModel(headerModel);

        binding.swipeRefreshLayout.setRefreshing(true);
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.load();
        });

        viewModel.load();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (android.R.id.home == id) {
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
