package com.sagar.watchnext.screens.people;

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


public class PeopleFragment extends Fragment implements PeopleFragmentMvpContract.View {
    PeopleFragmentComponent component;

    @Inject
    Presenter presenter;

    public PeopleFragment() {
        // Required empty public constructor
    }

    public static PeopleFragment newInstance() {
        PeopleFragment fragment = new PeopleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//              mParam1 = getArguments().getString("");
//        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivityComponent mainActivityComponent = ((MainActivity) getActivity()).getComponent();

        component
                = DaggerPeopleFragmentComponent.builder().
                mainActivityComponent(mainActivityComponent).
                peopleFragmentModule(new PeopleFragmentModule(this)).
                build();
        component.inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people, container, false);
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
