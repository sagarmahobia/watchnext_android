package com.sagar.watchnext.activities.list.showadapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.sagar.watchnext.response.PagingState;

import io.reactivex.disposables.CompositeDisposable;

public class ShowDataSourceFactory extends DataSource.Factory<Integer, ShowModel> {

    private CompositeDisposable disposable;
    private MutableLiveData<PagingState> stateLiveData;

    private ShowDataSource dataSource;

    public ShowDataSourceFactory(CompositeDisposable disposable,
                                 MutableLiveData<PagingState> stateLiveData
    ) {
        this.disposable = disposable;
        this.stateLiveData = stateLiveData;
    }

    @NonNull
    @Override
    public DataSource<Integer, ShowModel> create() {
        dataSource = new ShowDataSource(disposable, stateLiveData);
        return dataSource;
    }

    public void invalidate() {
        dataSource.invalidate();
    }
}
