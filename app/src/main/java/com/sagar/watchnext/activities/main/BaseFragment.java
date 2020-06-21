package com.sagar.watchnext.activities.main;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.sagar.watchnext.activities.list.ListActivity;
import com.sagar.watchnext.activities.moviedetail.MovieDetailActivity;
import com.sagar.watchnext.activities.tvdetail.TvDetailActivity;
import com.sagar.watchnext.adapters.card.CardAdapter;
import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.response.Response;
import com.sagar.watchnext.response.Status;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import java.util.List;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    protected void onResponse(Response response, CardAdapter cardAdapter, CardRecyclerModel recyclerModel) {
        stopSwipeRefresh();

        if (response.getStatus() == Status.SUCCESS) {

            List<CardModel> data = (List<CardModel>) response.getData();
            cardAdapter.submitList(data);
            recyclerModel.setStatus(CardRecyclerModel.Status.SUCCESS);

        } else if (response.getStatus() == Status.ERROR) {
            recyclerModel.setStatus(CardRecyclerModel.Status.ERROR);
        }
    }

    public void startMovieDetailActivity(int movieId) {

        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("movie_id", movieId);
        startActivity(intent);
    }

    public void startTvDetailActivity(int tv_id) {

        Intent intent = new Intent(getContext(), TvDetailActivity.class);
        intent.putExtra("tv_id", tv_id);
        startActivity(intent);
    }

    public void startList(String type, String subType, String title) {
        Intent intent = new Intent(getContext(), ListActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("subtype", subType);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    protected abstract void stopSwipeRefresh();

}
