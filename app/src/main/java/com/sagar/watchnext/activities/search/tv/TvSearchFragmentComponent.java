package com.sagar.watchnext.activities.search.tv;

import com.sagar.watchnext.activities.search.SearchActivityComponent;

import dagger.Component;

/**
 * Created by SAGAR MAHOBIA on 12-Jul-18. at 14:03
 */

@Component(modules = TvSearchFragmentModule.class, dependencies = SearchActivityComponent.class)
@TvSearchFragmentScope
public interface TvSearchFragmentComponent {
    void inject(TvSearchFragment tvSearchFragment);

    void inject(Presenter presenter);
}
