package com.sagar.watchnext.activities.list.showadapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.response.PagingState;

import io.reactivex.disposables.CompositeDisposable;

public class ShowDataSourceFactory extends DataSource.Factory<Integer, ShowModel> {

    private CompositeDisposable disposable;
    private MutableLiveData<PagingState> stateLiveData;

    private ShowDataSource dataSource;
    private String type;
    private String subtype;
    private int id;
    private TMDBRepository tmdbRepository;

    public ShowDataSourceFactory(CompositeDisposable disposable,
                                 MutableLiveData<PagingState> stateLiveData,
                                 String type, String subtype, int id, TMDBRepository tmdbRepository) {
        this.disposable = disposable;
        this.stateLiveData = stateLiveData;
        this.type = type;
        this.subtype = subtype;
        this.id = id;
        this.tmdbRepository = tmdbRepository;
    }

    @NonNull
    @Override
    public DataSource<Integer, ShowModel> create() {
        dataSource = new ShowDataSource(tmdbRepository, disposable, stateLiveData, type, subtype,id);
        return dataSource;
    }

    public void invalidate() {
        dataSource.invalidate();
    }
}
