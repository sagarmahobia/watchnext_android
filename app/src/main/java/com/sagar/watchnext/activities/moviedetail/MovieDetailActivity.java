package com.sagar.watchnext.activities.moviedetail;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.sagar.watchnext.R;
import com.sagar.watchnext.WatchNextApplicationComponent;
import com.sagar.watchnext.activities.list.ListActivity;
import com.sagar.watchnext.adapters.card.CardAdapter;
import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.adapters.video.VideoAdapter;
import com.sagar.watchnext.adapters.video.VideoModel;
import com.sagar.watchnext.databinding.ActivityMovieDetailBinding;
import com.sagar.watchnext.nativeadview.NativeTemplateStyle;
import com.sagar.watchnext.nativeadview.TemplateView;
import com.sagar.watchnext.observablemodels.ContentVisibilityModel;
import com.sagar.watchnext.observablemodels.HeaderModel;
import com.sagar.watchnext.response.Response;
import com.sagar.watchnext.response.Status;
import com.sagar.watchnext.utils.PixelDensityUtil;
import com.sagar.watchnext.viewmodelfactories.ApplicationViewModelFactory;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MovieDetailActivity extends AppCompatActivity {

    @Inject
    PixelDensityUtil pixelDensityUtil;

    @Inject
    ApplicationViewModelFactory viewModelFactory;

    @Inject
    CardAdapter recommendationAdapter;

    @Inject
    CardAdapter similarAdapter;

    @Inject
    VideoAdapter videoAdapter;

    @Inject
    WatchNextApplicationComponent component;


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
            supportActionBar.setTitle("Detail");

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

        viewModel = new ViewModelProvider(this, viewModelFactory).get(MovieDetailActivityViewModel.class);

        dataModel = viewModel.getDataModel();
        activityModel = viewModel.getActivityModel();
        headerModel = viewModel.getHeaderModel();
        visibilityModel = viewModel.getVisibilityModel();

        binding.swipeRefreshLayout.setRefreshing(true);
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.load();
        });

        dataModel.setMovieId(movie_id);

        binding.setModel(activityModel);
        binding.setVisibilityModel(visibilityModel);
        binding.header.setModel(headerModel);


        /*VIDEOS*/

        viewModel.getVideoCardRecycleModel().setTitle("Videos");

        binding.videos.horizontalListRecycler.setAdapter(videoAdapter);
        binding.videos.setModel(viewModel.getVideoCardRecycleModel());

        videoAdapter.setClickListener(videoModel -> {
            Intent youTubeIntent = new Intent(Intent.ACTION_VIEW);
            youTubeIntent.setData(Uri.parse(videoModel.getUrl()));
            startActivity(youTubeIntent);
        });

        /* Recommendation */

        binding.recommendations.setModel(viewModel.getRecommendationCardRecyclerModel());
        binding.recommendations.horizontalListRecycler.setAdapter(recommendationAdapter);

        viewModel.getRecommendationCardRecyclerModel().setTitle("Recommended");
        binding.recommendations.seeAll.setOnClickListener(v -> {

            Intent listActivityIntent = new Intent(this, ListActivity.class);
            listActivityIntent.putExtra("type", "movie");
            listActivityIntent.putExtra("subtype", "recommendations");
            listActivityIntent.putExtra("id", movie_id);
            startActivity(listActivityIntent);

        });

        recommendationAdapter.setAdapterListener(model -> {
            Intent thisActivityIntent = new Intent(this, MovieDetailActivity.class);
            thisActivityIntent.putExtra("movie_id", model.getId());
            supportActionBar.setTitle("Detail");
            startActivity(thisActivityIntent);

        });

        /*SIMILAR*/
        binding.similar.setModel(viewModel.getSimilarCardRecyclerModel());
        binding.similar.horizontalListRecycler.setAdapter(similarAdapter);

        viewModel.getSimilarCardRecyclerModel().setTitle("Similar");
        binding.similar.seeAll.setOnClickListener(v -> {

            Intent listActivityIntent = new Intent(this, ListActivity.class);
            listActivityIntent.putExtra("type", "movie");
            listActivityIntent.putExtra("subtype", "similar");
            listActivityIntent.putExtra("id", movie_id);
            startActivity(listActivityIntent);

        });

        similarAdapter.setAdapterListener(model -> {
            Intent thisActivityIntent = new Intent(this, MovieDetailActivity.class);
            thisActivityIntent.putExtra("movie_id", model.getId());
            startActivity(thisActivityIntent);

        });


        viewModel.load();

        viewModel.getRecommendationResponse().observe(this, this::observeRecommendationResponse);
        viewModel.getSimilarResponse().observe(this, this::observeSimilarResponse);
        viewModel.getVideosResponse().observe(this, this::observeVideoResponse);
        load();
    }

    void load() {
        AdLoader.Builder builder = new AdLoader.Builder(this, getString(R.string.detail_screen_native_ad));


        builder.forUnifiedNativeAd(unifiedNativeAd -> {
            NativeTemplateStyle styles = new
                    NativeTemplateStyle.Builder().withMainBackgroundColor(new ColorDrawable(Color.TRANSPARENT)).build();

            binding.midNativeAd.setVisibility(View.VISIBLE);
            TemplateView template = binding.midNativeAd;
            template.setStyles(styles);
            template.setNativeAd(unifiedNativeAd);

        });


        AdLoader adLoader = builder
                .build();

        adLoader.loadAd(component.adRequest());
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
            viewModel.getRecommendationCardRecyclerModel().setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            viewModel.getRecommendationCardRecyclerModel().setStatus(CardRecyclerModel.Status.ERROR);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (android.R.id.home == id) {
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
