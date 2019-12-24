package com.sagar.watchnext.activities.search.tv;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.search.ActivityStateObserver;
import com.sagar.watchnext.activities.search.SearchActivity;
import com.sagar.watchnext.activities.search.tv.adapters.RecyclerAdapter;
import com.sagar.watchnext.activities.tvdetail.TvDetailActivity;
import com.sagar.watchnext.adapters.listeners.EndlessRecyclerViewScrollListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvSearchFragment extends Fragment implements ActivityStateObserver, Contract.View {

    @Inject
    Contract.Presenter presenter;

    @Inject
    RecyclerAdapter adapter;

    @BindView(R.id.search_recycler_grid_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.error_icon_image_view)
    ImageView errorIcon;

    @BindView(R.id.error_message_text)
    TextView errorMessageTextView;

    @BindView(R.id.error_message_text_and_image)
    Group errorMessage;

    private String query = "";
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (query.isEmpty()) {
                hideProgress();
            } else {
                this.onQuery(query);
            }
        });

        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                presenter.onLoadMore(pageToLoad);
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SearchActivity activity = (SearchActivity) getActivity();
        if (activity != null) {
            activity.setActivityStateObserver(this);
        }

        getLifecycle().addObserver(presenter);

        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onQuery(String query) {
        this.query = query;
        presenter.query(query);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onErrorLoadingShowList() {
        showErrorMessage();
        hideProgress();
    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void resetEndlessLoader() {
        endlessRecyclerViewScrollListener.resetState();
    }


    @Override
    public void startTvDetailActivity(int tv_id) {

        Intent intent = new Intent(getContext(), TvDetailActivity.class);
        intent.putExtra("tv_id", tv_id);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void hideErrorMessage() {
        errorMessage.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        errorMessage.setVisibility(View.VISIBLE);
        errorIcon.setImageResource(R.drawable.ic_error_outline);
        errorMessageTextView.setText("Something went wrong");
    }

    @Override
    public void showNoMatchMessage() {
        errorMessage.setVisibility(View.VISIBLE);
        errorIcon.setImageResource(R.drawable.ic_sad_emoticon);
        errorMessageTextView.setText("No matches were found");
    }


}
