package com.sagar.watchnext.activities.main.tv;

import com.sagar.watchnext.activities.main.MainActivityComponent;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 16:41
 */
@TvFragmentScope
@Component(modules = TvFragmentModule.class, dependencies = MainActivityComponent.class)
public interface TvFragmentComponent {
    void inject(TvFragment tvFragment);

    void inject(Presenter presenter);
}
