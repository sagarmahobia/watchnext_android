package com.sagar.watchnext.activities.persondetail;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class PersonActivityViewModel extends ViewModel {

    private final CompositeDisposable disposable = new CompositeDisposable();

    private PersonActivityModel activityModel = new PersonActivityModel();
    ;

    public PersonActivityViewModel() {
    }

    public PersonActivityModel getActivityModel() {
        return activityModel;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
