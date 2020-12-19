package com.sagar.watchnext.activities.persondetail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

@PersonActivityScope
public class PersonActivityViewModelFactory implements ViewModelProvider.Factory {


    @Inject
    PersonActivityViewModelFactory() {
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(PersonActivityViewModel.class)) {
            return (T) new PersonActivityViewModel();
        } else {
            throw new IllegalArgumentException("Can not find ViewModel class " + modelClass.getName());
        }
    }
}
//@ContributesAndroidInjector(modules = {PersonActivityModule.class})
//@PersonActivityScope
//abstract PersonActivity bindPersonActivity();

