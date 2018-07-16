package com.sagar.watchnext.activities.search;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;

import com.sagar.watchnext.R;
import com.sagar.watchnext.WatchNextApplication;
import com.sagar.watchnext.activities.search.adapters.ViewPagerAdapter;
import com.sagar.watchnext.activities.search.movies.MovieSearchFragment;
import com.sagar.watchnext.activities.search.tv.TvSearchFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements TextWatcher {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.searchbar_edittext)
    EditText searchBarEditText;

    @BindView(R.id.clear_icon)
    ImageButton clearButton;

    @BindView(R.id.back_icon)
    ImageButton backButton;

    private ViewPagerAdapter viewPagerAdapter;

    private List<ActivityStateObserver> activityStateObservers;

    private SearchActivityComponent component;

    public SearchActivityComponent getComponent() {
        return component;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();

        ButterKnife.bind(this);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        activityStateObservers = new ArrayList<>();
        viewPagerAdapter.addFragment(new MovieSearchFragment(), "Movies");
        viewPagerAdapter.addFragment(new TvSearchFragment(), "Tv");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        searchBarEditText.addTextChangedListener(this);

        clearButton.setOnClickListener(view -> {
            searchBarEditText.setText("");
        });

        backButton.setOnClickListener(view -> {
            this.onBackPressed();
        });

        component = DaggerSearchActivityComponent.builder().
                watchNextApplicationComponent(((WatchNextApplication) getApplication()).getComponent()).
                build();
        component.injectSearchActivity(this);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public void setActivityStateObserver(ActivityStateObserver activityStateObserver) {
        activityStateObservers.add(activityStateObserver);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        for (ActivityStateObserver activityStateObserver : activityStateObservers) {
            activityStateObserver.onQuery(editable.toString());
        }
    }
}
