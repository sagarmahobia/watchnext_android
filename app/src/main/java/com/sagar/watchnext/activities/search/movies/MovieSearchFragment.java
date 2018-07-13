package com.sagar.watchnext.activities.search.movies;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.search.ActivityStateObserver;
import com.sagar.watchnext.activities.search.SearchActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieSearchFragment extends Fragment implements ActivityStateObserver {


    public MovieSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SearchActivity activity = (SearchActivity) getActivity();
        if (activity != null) {
            activity.setActivityStateObserver(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onQuery(String query) {

    }
}
