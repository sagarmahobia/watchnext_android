package com.sagar.watchnext.viewmodels;


import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class DisposableViewModel extends ViewModel {

    protected CompositeDisposable disposable;

    public DisposableViewModel() {
        disposable = new CompositeDisposable();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
