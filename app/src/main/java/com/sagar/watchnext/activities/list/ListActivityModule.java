package com.sagar.watchnext.activities.list;

import com.sagar.watchnext.activities.list.showadapter.ShowAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ListActivityModule {

    @Provides
    @ListActivityScope
    ShowAdapter showAdapter() {
        return new ShowAdapter();
    }
}
