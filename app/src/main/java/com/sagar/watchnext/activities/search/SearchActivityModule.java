package com.sagar.watchnext.activities.search;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 11-Jul-18. at 18:52
 */
@Module
public class SearchActivityModule {

    @Provides
    @SearchActivityScope
    SearchActivity provideSearchActivity(SearchActivity searchActivity) {
        return searchActivity;

    }
}