package com.sagar.watchnext.activities.tvdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.watchnext.R;
import com.sagar.watchnext.WatchNextApplicationComponent;
import com.sagar.watchnext.activities.BaseActivity;
import com.sagar.watchnext.activities.list.ListActivity;
import com.sagar.watchnext.adapters.card.CardAdapter;
import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.adapters.video.VideoAdapter;
import com.sagar.watchnext.adapters.video.VideoModel;
import com.sagar.watchnext.databinding.ActivityTvDetailBinding;
import com.sagar.watchnext.response.Response;
import com.sagar.watchnext.response.Status;
import com.sagar.watchnext.utils.PixelDensityUtil;
import com.sagar.watchnext.viewmodelfactories.ApplicationViewModelFactory;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class TvDetailActivity extends BaseActivity {

    @Inject
    ApplicationViewModelFactory viewModelFactory;

    @Inject
    Picasso picasso;

    @Inject
    PixelDensityUtil pixelDensityUtil;

    @Inject
    CardAdapter recommendationAdapter;

    @Inject
    CardAdapter similarAdapter;

    @Inject
    VideoAdapter videoAdapter;

    @Inject
    WatchNextApplicationComponent component;

    private int tv_id;

    private ActivityTvDetailBinding binding;
    private TvDetailActivityViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_detail);

        viewModel = new ViewModelProvider(this, viewModelFactory).get(TvDetailActivityViewModel.class);

        binding.setModel(viewModel.getActivityModel());
        binding.header.setModel(viewModel.getHeaderModel());
        binding.setVisibilityModel(viewModel.getVisibilityModel());

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle("Detail");
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

        /* Trailers*/


        viewModel.getVideoCardRecycleModel().setTitle("Videos");

        binding.videos.horizontalListRecycler.setAdapter(videoAdapter);
        binding.videos.setModel(viewModel.getVideoCardRecycleModel());

        videoAdapter.setClickListener(videoModel -> {
            openYoutube(videoModel.getUrl());
        });


        /* Recommendations */

        binding.recommendations.setModel(viewModel.getRecommendedCardRecyclerModel());
        binding.recommendations.horizontalListRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.recommendations.horizontalListRecycler.setAdapter(recommendationAdapter);

        viewModel.getRecommendedCardRecyclerModel().setTitle("Recommended");
        binding.recommendations.seeAll.setOnClickListener(v -> {

            Intent listActivityIntent = new Intent(this, ListActivity.class);
            listActivityIntent.putExtra("type", "tv");
            listActivityIntent.putExtra("subtype", "recommendations");
            listActivityIntent.putExtra("id", tv_id);
            startActivity(listActivityIntent);

        });

        recommendationAdapter.setAdapterListener(model -> {
            Intent thisActivityIntent = new Intent(this, TvDetailActivity.class);
            thisActivityIntent.putExtra("tv_id", model.getId());
            startActivity(thisActivityIntent);

        });
        /*Similar*/

        binding.similar.setModel(viewModel.getSimilarCardRecyclerModel());
        binding.similar.horizontalListRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.similar.horizontalListRecycler.setAdapter(similarAdapter);

        viewModel.getSimilarCardRecyclerModel().setTitle("Similar");
        binding.similar.seeAll.setOnClickListener(v -> {

            Intent listActivityIntent = new Intent(this, ListActivity.class);
            listActivityIntent.putExtra("type", "tv");
            listActivityIntent.putExtra("subtype", "similar");
            listActivityIntent.putExtra("id", tv_id);
            startActivity(listActivityIntent);

        });

        similarAdapter.setAdapterListener(model -> {
            Intent thisActivityIntent = new Intent(this, TvDetailActivity.class);
            thisActivityIntent.putExtra("tv_id", model.getId());
            startActivity(thisActivityIntent);

        });


        viewModel.load();
        viewModel.getRecommendationResponse().observe(this, this::observeRecommendationResponse);
        viewModel.getSimilarResponse().observe(this, this::observeSimilarResponse);
        viewModel.getVideosResponse().observe(this, this::observeVideoResponse);

        load(binding.midNativeAd);
    }

    private void observeVideoResponse(Response<List<VideoModel>> response) {

        if (response.getStatus() == Status.SUCCESS) {

            List<VideoModel> data = response.getData();
            videoAdapter.setVideoModels(data);

            if (data == null || data.isEmpty()) {
                binding.videos.root.setVisibility(View.GONE);
                return;
            }
            viewModel.getVideoCardRecycleModel().setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            viewModel.getVideoCardRecycleModel().setStatus(CardRecyclerModel.Status.ERROR);
        }

    }

    private void observeRecommendationResponse(Response<List<CardModel>> response) {
        if (response.getStatus() == Status.SUCCESS) {
            List<CardModel> data = response.getData();
            recommendationAdapter.submitList(data);
            if (data == null || data.isEmpty()) {
                binding.recommendations.root.setVisibility(View.GONE);
                return;
            }
            viewModel.getRecommendedCardRecyclerModel().setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            viewModel.getRecommendedCardRecyclerModel().setStatus(CardRecyclerModel.Status.ERROR);
        }
    }

    private void observeSimilarResponse(Response<List<CardModel>> response) {
        if (response.getStatus() == Status.SUCCESS) {
            List<CardModel> data = response.getData();
            similarAdapter.submitList(data);
            if (data == null || data.isEmpty()) {
                binding.similar.root.setVisibility(View.GONE);
                return;
            }
            viewModel.getSimilarCardRecyclerModel().setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            viewModel.getSimilarCardRecyclerModel().setStatus(CardRecyclerModel.Status.ERROR);
        }
    }
}