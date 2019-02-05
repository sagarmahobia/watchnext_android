package com.sagar.watchnext.activities.main.people;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.main.MainActivity;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class PeopleFragment extends Fragment implements Contract.View, View.OnClickListener {

    @Inject
    Presenter presenter;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
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
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("People");
        }
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

//        popularPeople.findViewById(R.id.see_all_button).setOnClickListener(this);

        linearLayout.addView(popularPeople);


        return scrollView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.see_all_button) {
//            Toast.makeText(getContext(), "stub . to be implemented", Toast.LENGTH_SHORT).show();
//        }
    }
}
