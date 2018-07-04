package com.sagar.watchnext.screens.people;

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


public class PeopleFragment extends Fragment implements PeopleFragmentMvpContract.View, View.OnClickListener {
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

        ScrollView scrollView = (ScrollView) inflater.inflate(R.layout.fragment_people, container, false);
        LinearLayout linearLayout = scrollView.findViewById(R.id.card_list_container);


        RelativeLayout popularPeople = (RelativeLayout) inflater.inflate(
                R.layout.card_horizontal_recycler,
                linearLayout, false);
        ((TextView) popularPeople.findViewById(R.id.card_header_text)).setText("Popular");

        popularPeople.findViewById(R.id.see_all_button).setOnClickListener(this);

        linearLayout.addView(popularPeople);


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
