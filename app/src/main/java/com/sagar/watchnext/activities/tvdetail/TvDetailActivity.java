package com.sagar.watchnext.activities.tvdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.sagar.watchnext.R;
import com.sagar.watchnext.databinding.ActivityTvDetailBinding;
import com.sagar.watchnext.utils.PixelDensityUtil;
import com.sagar.watchnext.viewmodelfactories.ApplicationViewModelFactory;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class TvDetailActivity extends AppCompatActivity {


    @Inject
    ApplicationViewModelFactory viewModelFactory;

    @Inject
    Picasso picasso;

    @Inject
    PixelDensityUtil pixelDensityUtil;

    private int tv_id;

    private ActivityTvDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_detail);

        TvDetailActivityViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(TvDetailActivityViewModel.class);

        binding.setModel(viewModel.getActivityModel());
        binding.header.setModel(viewModel.getHeaderModel());
        binding.setVisibilityModel(viewModel.getVisibilityModel());

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        pixelDensityUtil.getBackDropImageHeight()
                );
        binding.header.backdropImage.setLayoutParams(lp);

        Intent intent = getIntent();
        tv_id = intent.getIntExtra("tv_id", -1);

        viewModel.getDataModel().setTvId(tv_id);

        if (tv_id == -1) {
            //todo handle error in getting tv_id
            this.finish();
            return;
        }
        binding.swipeRefreshLayout.setOnRefreshListener(() -> viewModel.load());
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