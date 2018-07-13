package com.sagar.watchnext.activities.search;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SAGAR MAHOBIA on 11-Jul-18. at 18:52
 */
@Module
public class SearchActivityModule {
    private SearchActivity searchActivity;


    public SearchActivityModule(SearchActivity searchActivity) {
        this.searchActivity = searchActivity;
    }

    @Provides
    @SearchActivityScope
    SearchActivity provideSearchActivity() {
        return this.searchActivity;
    }



}
