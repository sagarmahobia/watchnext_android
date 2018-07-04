package com.sagar.watchnext.screens.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.sagar.watchnext.screens.home.adapters.MovieRecyclerAdapter;
import com.sagar.watchnext.screens.home.adapters.TvRecyclerAdapter;

import javax.inject.Inject;


public class HomeFragment extends Fragment implements HomeFragmentMvpContract.View {

    private RecyclerView recyclerViewOnTv;
    private RecyclerView recyclerViewInTheaters;

    private RelativeLayout inTheatersCard;
    private RelativeLayout onTvCard;

    @Inject
    TvRecyclerAdapter onTvRecyclerAdapter;

    @Inject
    MovieRecyclerAdapter inTheatersRecyclerAdapter;

    @Inject
    HomeFragmentMvpContract.Presenter presenter;


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {

        HomeFragment fragment = new HomeFragment();
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

        ScrollView scrollView = (ScrollView) inflater.inflate(R.layout.fragment_home, container, false);
        LinearLayout linearLayout = scrollView.findViewById(R.id.card_list_container);


        inTheatersCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);

        onTvCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);

        ((TextView) inTheatersCard.findViewById(R.id.card_header_text)).setText("In Theaters");
        ((TextView) onTvCard.findViewById(R.id.card_header_text)).setText("On TV");

        //todo modify on click listener
        inTheatersCard.findViewById(R.id.see_all_button).setOnClickListener(v -> showToast("Feature N/A"));

        onTvCard.findViewById(R.id.see_all_button).setOnClickListener(v -> showToast("Feature N/A"));

        recyclerViewOnTv = onTvCard.findViewById(R.id.horizontal_list_recycler);
        recyclerViewInTheaters = inTheatersCard.findViewById(R.id.horizontal_list_recycler);

        LinearLayoutManager horizontalLayoutManagerForTv
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManagerForMovie
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewOnTv.setLayoutManager(horizontalLayoutManagerForTv);
        recyclerViewInTheaters.setLayoutManager(horizontalLayoutManagerForMovie);


        linearLayout.addView(inTheatersCard);
        linearLayout.addView(onTvCard);
        Log.d("Lifecycle", "On create view called");

        return scrollView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("WatchNext - Home");
        }
        MainActivityComponent mainActivityComponent = ((MainActivity) getActivity()).getComponent();

        DaggerHomeFragmentComponent.builder().
                mainActivityComponent(mainActivityComponent)
                .homeFragmentModule(new HomeFragmentModule(this))
                .build().inject(this);
        presenter.onCreate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
        Log.d("Lifecycle", "On destroy view called");
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onErrorLoadingMovieList() {
        inTheatersCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
        inTheatersCard.findViewById(R.id.progressBar).setVisibility(View.GONE);

    }

    @Override
    public void onSucceedLoadingMovieList() {
        recyclerViewInTheaters.setAdapter(inTheatersRecyclerAdapter);
        inTheatersCard.findViewById(R.id.progressBar).setVisibility(View.GONE);

    }

    @Override
    public void onSucceedLoadingTvList() {
        recyclerViewOnTv.setAdapter(onTvRecyclerAdapter);
        onTvCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoadingTvList() {
        onTvCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
        onTvCard.findViewById(R.id.progressBar).setVisibility(View.GONE);
    }
}
