package com.sagar.watchnext.screens.tv;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

import javax.inject.Inject;


public class TvFragment extends Fragment implements TvFragmentMvpContract.View, View.OnClickListener {

    TvFragmentComponent component;

    @Inject
    Presenter presenter;

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivityComponent mainActivityComponent = ((MainActivity) getActivity()).getComponent();

        component = DaggerTvFragmentComponent.builder()
                .mainActivityComponent(mainActivityComponent)
                .tvFragmentModule(new TvFragmentModule(this))
                .build();
        component.inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ScrollView scrollView = (ScrollView) inflater.inflate(R.layout.fragment_tv, container, false);
        LinearLayout linearLayout = scrollView.findViewById(R.id.card_list_container);


        RelativeLayout airingTodayTvCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);

        RelativeLayout onTheAirTvCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);

        RelativeLayout popularTvCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);

        RelativeLayout topRatedTvCard = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);


        ((TextView) airingTodayTvCard.findViewById(R.id.card_header_text)).setText("Airing Today");
        ((TextView) onTheAirTvCard.findViewById(R.id.card_header_text)).setText("On The Air");
        ((TextView) popularTvCard.findViewById(R.id.card_header_text)).setText("Popular");
        ((TextView) topRatedTvCard.findViewById(R.id.card_header_text)).setText("Top Rated");

        airingTodayTvCard.findViewById(R.id.see_all_button).setOnClickListener(this);
        onTheAirTvCard.findViewById(R.id.see_all_button).setOnClickListener(this);
        popularTvCard.findViewById(R.id.see_all_button).setOnClickListener(this);
        topRatedTvCard.findViewById(R.id.see_all_button).setOnClickListener(this);


        linearLayout.addView(airingTodayTvCard);
        linearLayout.addView(onTheAirTvCard);
        linearLayout.addView(popularTvCard);
        linearLayout.addView(topRatedTvCard);


        return scrollView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.see_all_button) {
            Toast.makeText(getContext(), "stub . to be implemented", Toast.LENGTH_SHORT).show();
        }
    }
}
