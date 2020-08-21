package com.sagar.watchnext.activities.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sagar.watchnext.R;
import com.sagar.watchnext.activities.main.home.HomeFragment;
import com.sagar.watchnext.activities.main.movies.MoviesFragment;
import com.sagar.watchnext.activities.main.tv.TvFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class MainActivity extends AppCompatActivity implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> fragmentDispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        ((BottomNavigationView) findViewById(R.id.bottom_navigation)).setOnNavigationItemSelectedListener(v -> {
            int id = v.getItemId();
            Fragment fragment = null;
            if (id == R.id.home) {
                fragment = new HomeFragment();

            } else if (id == R.id.movies) {
                fragment = new MoviesFragment();

            } else if (id == R.id.tv) {
                fragment = new TvFragment();

            }
            if (fragment != null) {
                transactFragment(fragment);
            }
            return true;
        });

        transactFragment(new HomeFragment());
    }

    /*

     String shareBody = "WatchNext - Discover Movies & TV. \n" +
       "https://play.google.com/store/apps/details?id=com.sagar.watchnext";
       Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
       sharingIntent.setType("text/plain");
       sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
       this.startActivity(Intent.createChooser(sharingIntent, "Share via"));

    */
    void transactFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_view, fragment);
        ft.commit();
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
