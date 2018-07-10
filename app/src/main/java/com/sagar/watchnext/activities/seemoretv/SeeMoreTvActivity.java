package com.sagar.watchnext.activities.seemoretv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sagar.watchnext.R;

import javax.inject.Inject;

public class SeeMoreTvActivity extends AppCompatActivity implements SeeMoreTvActivityMvpContract.View {


    @Inject
    SeeMoreTvActivityMvpContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_tv);

    }
}
