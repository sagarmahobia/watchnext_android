package com.sagar.watchnext.activities.list.showadapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.ItemKeyedDataSource;

import com.sagar.watchnext.response.PagingState;

import io.reactivex.disposables.CompositeDisposable;

public class ShowDataSource extends ItemKeyedDataSource<Integer, ShowModel> {

    private CompositeDisposable disposable;
    private MutableLiveData<PagingState> stateLiveData;

    private int page = 0;

    ShowDataSource(
            @NonNull CompositeDisposable disposable,
            @NonNull MutableLiveData<PagingState> stateLiveData
    ) {

        this.disposable = disposable;
        this.stateLiveData = stateLiveData;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<ShowModel> callback) {
        stateLiveData.postValue(PagingState.loading());
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<ShowModel> callback) {
        stateLiveData.postValue(PagingState.loading());
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<ShowModel> callback) {
        // do nothing
    }

    @NonNull
    @Override
    public Integer getKey(@NonNull ShowModel item) {
        return item.getId();
    }
}
