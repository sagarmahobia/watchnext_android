package com.sagar.watchnext.screens.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sagar.watchnext.R;
import com.sagar.watchnext.screens.MainActivity;
import com.sagar.watchnext.screens.MainActivityComponent;
import com.sagar.watchnext.screens.home.adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class HomeFragment extends Fragment implements HomeFragmentMvpContract.View {

    private RecyclerView recyclerViewOnTv;
    private RecyclerView recyclerViewInTheaters;

    private RelativeLayout inTheatersCard;
    private RelativeLayout onTvCard;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    RecyclerAdapter onTvRecyclerAdapter;

    @Inject
    RecyclerAdapter inTheatersRecyclerAdapter;

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

        swipeRefreshLayout = (SwipeRefreshLayout) inflater.inflate(R.layout.fragment_base, container, false);
        LinearLayout linearLayout = swipeRefreshLayout.findViewById(R.id.card_list_container);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.onCreate());

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

        List<LinearLayoutManager> linearLayoutManagers = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            linearLayoutManagers.add(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }

        recyclerViewOnTv.setLayoutManager(linearLayoutManagers.get(0));
        recyclerViewInTheaters.setLayoutManager(linearLayoutManagers.get(1));


        linearLayout.addView(inTheatersCard);
        linearLayout.addView(onTvCard);

        Log.d("Lifecycle", "On create view called");

        return swipeRefreshLayout;
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

        onTvRecyclerAdapter.setListType(ListType.OnTv);
        inTheatersRecyclerAdapter.setListType(ListType.InTheaters);

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
    public void onSucceedLoadingList(ListType listType) {
        switch (listType) {
            case InTheaters:
                recyclerViewInTheaters.setAdapter(inTheatersRecyclerAdapter);
                inTheatersCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                inTheatersCard.findViewById(R.id.error_text).setVisibility(View.GONE);
                break;
            default:
                recyclerViewOnTv.setAdapter(onTvRecyclerAdapter);
                onTvCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
                onTvCard.findViewById(R.id.error_text).setVisibility(View.GONE);

        }
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onErrorLoadingList(ListType listType) {
        switch (listType) {
            case InTheaters:
                inTheatersCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
                inTheatersCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);

                break;
            default:
                onTvCard.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
                onTvCard.findViewById(R.id.please_wait_text).setVisibility(View.GONE);
        }
        swipeRefreshLayout.setRefreshing(false);
    }
}
