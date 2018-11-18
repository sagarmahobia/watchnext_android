package com.sagar.watchnext.activities.main.home;


import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 30-Jun-18. at 16:30
 */

@Module
class HomeFragmentModule {

    private HomeFragment homeFragment;

    HomeFragmentModule(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Provides
    @HomeFragmentScope
    HomeFragment getHomeFragment() {
        return this.homeFragment;
    }

    @Provides
    @HomeFragmentScope
    Contract.View getView(HomeFragment homeFragment) {
        return homeFragment;
    }

    @Provides
    @HomeFragmentScope
    Contract.Presenter getPresenter(Presenter presenter) {
        return presenter;
    }
}
