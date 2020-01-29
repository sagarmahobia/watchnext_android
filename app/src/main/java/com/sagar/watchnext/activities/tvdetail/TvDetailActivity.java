package com.sagar.watchnext.activities.tvdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.list.ListActivity;
import com.sagar.watchnext.adapters.card.CardAdapter;
import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.databinding.ActivityTvDetailBinding;
import com.sagar.watchnext.response.Response;
import com.sagar.watchnext.response.Status;
import com.sagar.watchnext.utils.PixelDensityUtil;
import com.sagar.watchnext.viewmodelfactories.ApplicationViewModelFactory;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class TvDetailActivity extends AppCompatActivity {


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

    private int tv_id;

    private ActivityTvDetailBinding binding;
    private TvDetailActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_detail);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TvDetailActivityViewModel.class);

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
    }

    private void observeRecommendationResponse(Response<List<CardModel>> response) {
        if (response.getStatus() == Status.SUCCESS) {
            List<CardModel> data = response.getData();
            recommendationAdapter.submitList(data);

            viewModel.getRecommendedCardRecyclerModel().setStatus(CardRecyclerModel.Status.SUCCESS);
        } else if (response.getStatus() == Status.ERROR) {

            viewModel.getRecommendedCardRecyclerModel().setStatus(CardRecyclerModel.Status.ERROR);
        }
    }

    private void observeSimilarResponse(Response<List<CardModel>> response) {
        if (response.getStatus() == Status.SUCCESS) {
            List<CardModel> data = response.getData();
            similarAdapter.submitList(data);

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