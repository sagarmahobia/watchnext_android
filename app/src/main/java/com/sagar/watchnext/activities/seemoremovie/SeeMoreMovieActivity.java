package com.sagar.watchnext.activities.seemoremovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sagar.watchnext.R;
import com.sagar.watchnext.WatchNextApplication;

import javax.inject.Inject;

public class SeeMoreMovieActivity extends AppCompatActivity implements SeeMoreMovieActivityMvpContract.View {


    @Inject
    SeeMoreMovieActivityMvpContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_movie);

        DaggerSeeMoreMovieActivityComponent.
                builder().
                seeMoreMovieActivityModule(new SeeMoreMovieActivityModule(this)).
                watchNextApplicationComponent(WatchNextApplication.get(this).getComponent()).
                build().
                injectSeeMoreMovieActivity(this);

    }
}
