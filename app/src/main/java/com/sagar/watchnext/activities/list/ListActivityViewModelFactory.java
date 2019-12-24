package com.sagar.watchnext.activities.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

@ListActivityScope
public class ListActivityViewModelFactory implements ViewModelProvider.Factory {


    @Inject
    ListActivityViewModelFactory() {
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(ListActivityViewModel.class)) {
            return (T) new ListActivityViewModel();
        } else {
            throw new IllegalArgumentException("Can not find ViewModel class " + modelClass.getName());
        }
    }
}
//@ContributesAndroidInjector(modules = {ListActivityModule.class})
//ListActivityScope
//abstract ListActivity bindListActivity();

