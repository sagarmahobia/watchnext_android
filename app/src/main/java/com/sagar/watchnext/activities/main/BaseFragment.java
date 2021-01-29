package com.sagar.watchnext.activities.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.sagar.watchnext.R;
import com.sagar.watchnext.WatchNextApplicationComponent;
import com.sagar.watchnext.activities.list.ListActivity;
import com.sagar.watchnext.activities.moviedetail.MovieDetailActivity;
import com.sagar.watchnext.activities.tvdetail.TvDetailActivity;
import com.sagar.watchnext.adapters.card.CardAdapter;
import com.sagar.watchnext.adapters.card.CardModel;
import com.sagar.watchnext.adapters.people.PeopleAdapter;
import com.sagar.watchnext.adapters.people.PeopleModel;
import com.sagar.watchnext.response.Response;
import com.sagar.watchnext.response.Status;
import com.sagar.watchnext.utils.SharedPreferenceService;
import com.sagar.watchnext.views.cardrecycler.CardRecyclerModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends Fragment {

    @Inject
    SharedPreferenceService sharedPreferenceService;

    @Inject
    WatchNextApplicationComponent component;

    private static boolean asked;
    private Context context;

    @Override
    public void onAttach(Context context) {
        this.context = context;
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

    protected void onResponse(Response response, PeopleAdapter cardAdapter, CardRecyclerModel recyclerModel) {
        stopSwipeRefresh();

        if (response.getStatus() == Status.SUCCESS) {

            List<PeopleModel> data = (List<PeopleModel>) response.getData();
            cardAdapter.submitList(data);
            recyclerModel.setStatus(CardRecyclerModel.Status.SUCCESS);

        } else if (response.getStatus() == Status.ERROR) {
            recyclerModel.setStatus(CardRecyclerModel.Status.ERROR);
        }
    }

    public void startMovieDetailActivity(int movieId) {

        checkRatedElseOpen(() -> {


            Intent intent = new Intent(getContext(), MovieDetailActivity.class);
            intent.putExtra("movie_id", movieId);
            startActivity(intent);
        });

    }

    public void startTvDetailActivity(int tv_id) {

        checkRatedElseOpen(() -> {

            Intent intent = new Intent(getContext(), TvDetailActivity.class);
            intent.putExtra("tv_id", tv_id);
            startActivity(intent);
        });
    }

    void checkRatedElseOpen(Callable callable) {

        boolean openedEarlier = sharedPreferenceService.checkRated();
        long launchCount = sharedPreferenceService.getLaunchCount();
        if (launchCount % 4 == 0 && !openedEarlier && !asked) {
            showRateUsDialog(callable);
            asked = true;
        } else {
            callable.run();
        }

    }

    interface Callable {
        void run();
    }

    private void showRateUsDialog(Callable callable) {

        new AlertDialog.Builder(context, R.style.MyAlertDialogTheme)
                .setTitle("⭐⭐⭐⭐⭐")
                .setMessage("We need your support. If this app is useful to you, please take a moment to rate it.")
                .setPositiveButton("Rate", (dialog, which) -> {
                    dialog.dismiss();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())
                    );
                    startActivity(browserIntent);
                    sharedPreferenceService.setRated();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                    callable.run();

                })
                .setCancelable(false)
                .create()
                .show();
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
