package com.sagar.watchnext.activities.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.search.adapters.ViewPagerAdapter;
import com.sagar.watchnext.activities.search.movies.MovieSearchFragment;
import com.sagar.watchnext.activities.search.tv.TvSearchFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class SearchActivity extends AppCompatActivity implements TextWatcher, HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> fragmentDispatchingAndroidInjector;


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

    private List<ActivityStateObserver> activityStateObservers;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();

        ButterKnife.bind(this);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        activityStateObservers = new ArrayList<>();
        viewPagerAdapter.addFragment(new MovieSearchFragment(), "Movies");
        viewPagerAdapter.addFragment(new TvSearchFragment(), "Tv");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        searchBarEditText.addTextChangedListener(this);

        clearButton.setOnClickListener(view -> searchBarEditText.setText(""));

        backButton.setOnClickListener(view -> this.onBackPressed());


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


    @Override
    public AndroidInjector<Object> androidInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
