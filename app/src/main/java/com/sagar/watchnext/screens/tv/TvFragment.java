package com.sagar.watchnext.screens.tv;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.sagar.watchnext.screens.tv.Adapters.AiringTodayAdapter;
import com.sagar.watchnext.screens.tv.Adapters.OnTheAirAdapter;
import com.sagar.watchnext.screens.tv.Adapters.PopularAdapter;
import com.sagar.watchnext.screens.tv.Adapters.TopRatedAdapter;

import javax.inject.Inject;


public class TvFragment extends Fragment implements TvFragmentMvpContract.View, View.OnClickListener {

    @Inject
    Presenter presenter;

    RelativeLayout airingTodayTvCard;
    RelativeLayout onTheAirTvCard;
    RelativeLayout popularTvCard;
    RelativeLayout topRatedTvCard;

    RecyclerView airingTodayRecycler;
    RecyclerView onTheAirRecycler;
    RecyclerView popularRecycler;
    RecyclerView topRatedRecycler;

    @Inject
    AiringTodayAdapter airingTodayAdapter;

    @Inject
    OnTheAirAdapter onTheAirAdapter;

    @Inject
    PopularAdapter popularAdapter;

    @Inject
    TopRatedAdapter topRatedAdapter;

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

        ScrollView scrollView = (ScrollView) inflater.inflate(R.layout.fragment_tv, container, false);
        LinearLayout linearLayout = scrollView.findViewById(R.id.card_list_container);


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

        LinearLayoutManager horizontalLayoutManagerForAiringToday
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManagerForOnTheAir
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManagerForPopular
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManagerForTopRated
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        airingTodayRecycler.setLayoutManager(horizontalLayoutManagerForAiringToday);
        onTheAirRecycler.setLayoutManager(horizontalLayoutManagerForOnTheAir);
        popularRecycler.setLayoutManager(horizontalLayoutManagerForPopular);
        topRatedRecycler.setLayoutManager(horizontalLayoutManagerForTopRated);

        linearLayout.addView(airingTodayTvCard);
        linearLayout.addView(onTheAirTvCard);
        linearLayout.addView(popularTvCard);
        linearLayout.addView(topRatedTvCard);


        return scrollView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivityComponent mainActivityComponent = ((MainActivity) getActivity()).getComponent();

        DaggerTvFragmentComponent.builder()
                .mainActivityComponent(mainActivityComponent)
                .tvFragmentModule(new TvFragmentModule(this))
                .build().inject(this);
        presenter.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.see_all_button) {
            showToast("Feature NA");
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSucceedLoadingAiringTodayShowList() {
        airingTodayRecycler.setAdapter(airingTodayAdapter);
        airingTodayTvCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoadingAiringTodayShowList() {
        airingTodayTvCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
        airingTodayTvCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onSucceedLoadingOnTheAirShowList() {
        onTheAirRecycler.setAdapter(onTheAirAdapter);
        onTheAirTvCard.findViewById(R.id.progressBar).setVisibility(View.GONE);

    }

    @Override
    public void onErrorLoadingOnTheAirShowList() {
        onTheAirTvCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
        onTheAirTvCard.findViewById(R.id.progressBar).setVisibility(View.GONE);

    }

    @Override
    public void onSucceedLoadingPopularList() {
        popularRecycler.setAdapter(popularAdapter);
        popularTvCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoadingPopularList() {
        popularTvCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
        popularTvCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onSucceedLoadingTopRatedShowList() {
        topRatedRecycler.setAdapter(topRatedAdapter);
        topRatedTvCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoadingTopRatedShowList() {
        topRatedTvCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
        topRatedTvCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }
}
