package com.sagar.watchnext.screens.tv;

import com.sagar.watchnext.screens.MainActivityComponent;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 16:41
 */
@TvFragmentScope
@Component(modules = TvFragmentModule.class, dependencies = MainActivityComponent.class)
public interface TvFragmentComponent {
    void inject(TvFragment tvFragment);
}
