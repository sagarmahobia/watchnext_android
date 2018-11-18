package com.sagar.watchnext.activities.main.tv;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.main.MainActivity;
import com.sagar.watchnext.activities.main.MainActivityComponent;
import com.sagar.watchnext.activities.main.tv.adapters.RecyclerAdapter;
import com.sagar.watchnext.activities.tvdetail.TvDetailActivity;
import com.sagar.watchnext.adapters.listeners.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class TvFragment extends Fragment implements Contract.View {

    @Inject
    Contract.Presenter presenter;

    private RelativeLayout airingTodayTvCard;
    private RelativeLayout onTheAirTvCard;
    private RelativeLayout popularTvCard;
    private RelativeLayout topRatedTvCard;

    private RecyclerView airingTodayRecycler;
    private RecyclerView onTheAirRecycler;
    private RecyclerView popularRecycler;
    private RecyclerView topRatedRecycler;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    RecyclerAdapter airingTodayAdapter;

    @Inject
    RecyclerAdapter onTheAirAdapter;

    @Inject
    RecyclerAdapter popularAdapter;

    @Inject
    RecyclerAdapter topRatedAdapter;

    public TvFragment() {
        // Required empty public constructor
    }

    public static TvFragment newInstance() {
        TvFragment fragment = new TvFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        swipeRefreshLayout = (SwipeRefreshLayout) inflater.inflate(R.layout.fragment_base, container, false);
        LinearLayout linearLayout = swipeRefreshLayout.findViewById(R.id.card_list_container);

        airingTodayTvCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);

        onTheAirTvCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);

        popularTvCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);

        topRatedTvCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);


        ((TextView) airingTodayTvCard.findViewById(R.id.card_header_text)).setText("Airing Today");
        ((TextView) onTheAirTvCard.findViewById(R.id.card_header_text)).setText("On The Air");
        ((TextView) popularTvCard.findViewById(R.id.card_header_text)).setText("Popular");
        ((TextView) topRatedTvCard.findViewById(R.id.card_header_text)).setText("Top Rated");

        airingTodayRecycler = airingTodayTvCard.findViewById(R.id.horizontal_list_recycler);
        onTheAirRecycler = onTheAirTvCard.findViewById(R.id.horizontal_list_recycler);
        popularRecycler = popularTvCard.findViewById(R.id.horizontal_list_recycler);
        topRatedRecycler = topRatedTvCard.findViewById(R.id.horizontal_list_recycler);

        List<LinearLayoutManager> linearLayoutManagers = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            linearLayoutManagers.add(new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false));
        }

        airingTodayRecycler.setLayoutManager(linearLayoutManagers.get(0));
        onTheAirRecycler.setLayoutManager(linearLayoutManagers.get(1));
        popularRecycler.setLayoutManager(linearLayoutManagers.get(2));
        topRatedRecycler.setLayoutManager(linearLayoutManagers.get(3));

        EndlessRecyclerViewScrollListener scrollListenerForAiringToday =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) airingTodayRecycler.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        presenter.loadMore(ListType.AiringToday, pageToLoad);
                        airingTodayTvCard.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

                    }
                };
        EndlessRecyclerViewScrollListener scrollListenerForOnTheAir =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) onTheAirRecycler.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        presenter.loadMore(ListType.OnTheAir, pageToLoad);
                        onTheAirTvCard.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                    }
                };
        EndlessRecyclerViewScrollListener scrollListenerForPopular =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) popularRecycler.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        presenter.loadMore(ListType.Popular, pageToLoad);
                        popularTvCard.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

                    }
                };
        EndlessRecyclerViewScrollListener scrollListenerForTopRated =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) topRatedRecycler.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int pageToLoad, int totalItemsCount, RecyclerView view) {
                        presenter.loadMore(ListType.TopRated, pageToLoad);
                        topRatedTvCard.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

                    }
                };

        airingTodayRecycler.addOnScrollListener(scrollListenerForAiringToday);
        onTheAirRecycler.addOnScrollListener(scrollListenerForOnTheAir);
        popularRecycler.addOnScrollListener(scrollListenerForPopular);
        topRatedRecycler.addOnScrollListener(scrollListenerForTopRated);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.load();
            scrollListenerForAiringToday.resetState();
            scrollListenerForOnTheAir.resetState();
            scrollListenerForPopular.resetState();
            scrollListenerForTopRated.resetState();
        });

        linearLayout.addView(airingTodayTvCard);
        linearLayout.addView(onTheAirTvCard);
        linearLayout.addView(popularTvCard);
        linearLayout.addView(topRatedTvCard);

        swipeRefreshLayout.setRefreshing(true);

        return swipeRefreshLayout;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivityComponent mainActivityComponent = ((MainActivity) getActivity()).getComponent();
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("TV Shows");
        }
        DaggerTvFragmentComponent.builder()
                .mainActivityComponent(mainActivityComponent)
                .tvFragmentModule(new TvFragmentModule(this))
                .build().inject(this);

        getLifecycle().addObserver(presenter);

        airingTodayAdapter.setListType(ListType.AiringToday);
        onTheAirAdapter.setListType(ListType.OnTheAir);
        popularAdapter.setListType(ListType.Popular);
        topRatedAdapter.setListType(ListType.TopRated);

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSucceedLoadingShowList(ListType listType) {
        swipeRefreshLayout.setRefreshing(false);

        switch (listType) {
            case AiringToday:
                airingTodayRecycler.setAdapter(airingTodayAdapter);
                airingTodayTvCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                airingTodayTvCard.findViewById(R.id.error_text).setVisibility(View.GONE);
                break;
            case OnTheAir:
                onTheAirRecycler.setAdapter(onTheAirAdapter);
                onTheAirTvCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                onTheAirTvCard.findViewById(R.id.error_text).setVisibility(View.GONE);
                break;
            case Popular:
                popularRecycler.setAdapter(popularAdapter);
                popularTvCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                popularTvCard.findViewById(R.id.error_text).setVisibility(View.GONE);
                break;
            default://top Rated
                topRatedRecycler.setAdapter(topRatedAdapter);
                topRatedTvCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                topRatedTvCard.findViewById(R.id.error_text).setVisibility(View.GONE);

                break;
        }
    }

    @Override
    public void onErrorLoadingShowList(ListType listType) {
        swipeRefreshLayout.setRefreshing(false);

        switch (listType) {
            case AiringToday:
                airingTodayTvCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
                airingTodayTvCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                break;
            case OnTheAir:
                onTheAirTvCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
                onTheAirTvCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                break;
            case Popular:
                popularTvCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
                popularTvCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                break;
            default://top Rated
                topRatedTvCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
                topRatedTvCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void startTvDetailActivity(int tv_id) {
        Intent intent = new Intent(getContext(), TvDetailActivity.class);
        intent.putExtra("tv_id", tv_id);
        startActivity(intent);
    }

    @Override
    public void notifyAdaptersNewData(ListType listType) {

        switch (listType) {
            case AiringToday:
                airingTodayAdapter.notifyDataSetChanged();
                airingTodayTvCard.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                break;
            case OnTheAir:
                onTheAirAdapter.notifyDataSetChanged();
                onTheAirTvCard.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                break;
            case Popular:
                popularAdapter.notifyDataSetChanged();
                popularTvCard.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                break;
            case TopRated:
                topRatedAdapter.notifyDataSetChanged();
                topRatedTvCard.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                break;
        }
    }
}
