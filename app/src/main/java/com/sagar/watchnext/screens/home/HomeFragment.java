package com.sagar.watchnext.screens.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sagar.watchnext.R;
import com.sagar.watchnext.screens.MainActivity;
import com.sagar.watchnext.screens.MainActivityComponent;

import javax.inject.Inject;


public class HomeFragment extends Fragment implements HomeFragmentMvpContract.View {

    HomeFragmentComponent component;

    @Inject
    Presenter presenter;


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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivityComponent mainActivityComponent = ((MainActivity) getActivity()).getComponent();

        this.component = DaggerHomeFragmentComponent.builder().
                mainActivityComponent(mainActivityComponent)
                .homeFragmentModule(new HomeFragmentModule(this))
                .build();
        component.inject(this);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
