package com.sagar.watchnext.screens.tv;

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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.sagar.watchnext.R;
import com.sagar.watchnext.screens.MainActivity;
import com.sagar.watchnext.screens.MainActivityComponent;
import com.sagar.watchnext.screens.tv.Adapters.RecyclerAdapter;
import com.sagar.watchnext.screens.tvdetailactivity.TvDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class TvFragment extends Fragment implements TvFragmentMvpContract.View {

    @Inject
    Presenter presenter;

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
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString("");
//        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        swipeRefreshLayout = (SwipeRefreshLayout) inflater.inflate(R.layout.fragment_base, container, false);
        LinearLayout linearLayout = swipeRefreshLayout.findViewById(R.id.card_list_container);

        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.onCreate());

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

        airingTodayTvCard.findViewById(R.id.see_all_button).setOnClickListener(v -> showToast("Feature NA"));
        onTheAirTvCard.findViewById(R.id.see_all_button).setOnClickListener(v -> showToast("Feature NA"));
        popularTvCard.findViewById(R.id.see_all_button).setOnClickListener(v -> showToast("Feature NA"));
        topRatedTvCard.findViewById(R.id.see_all_button).setOnClickListener(v -> showToast("Feature NA"));


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

        linearLayout.addView(airingTodayTvCard);
        linearLayout.addView(onTheAirTvCard);
        linearLayout.addView(popularTvCard);
        linearLayout.addView(topRatedTvCard);


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

        airingTodayAdapter.setListType(ListType.AiringToday);
        onTheAirAdapter.setListType(ListType.OnTheAir);
        popularAdapter.setListType(ListType.Popular);
        topRatedAdapter.setListType(ListType.TopRated);

        presenter.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
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
        intent.putExtra("tv_id",tv_id);
        startActivity(intent);
    }
}
