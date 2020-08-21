package com.sagar.watchnext.activities.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.sagar.watchnext.activities.list.showadapter.ShowDataSourceFactory;
import com.sagar.watchnext.activities.list.showadapter.ShowModel;
import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.response.PagingState;

import io.reactivex.disposables.CompositeDisposable;

public class ListActivityViewModel extends ViewModel {


    private final CompositeDisposable disposable = new CompositeDisposable();

    private ListActivityModel activityModel = new ListActivityModel();

    private MutableLiveData<PagingState> pagedListStateLiveData = new MutableLiveData<>();
    private ShowDataSourceFactory showDataSourceFactory;
    private LiveData<PagedList<ShowModel>> pagedListLiveData;
    private TMDBRepository tmdbRepository;

    public ListActivityViewModel(TMDBRepository tmdbRepository) {

        this.tmdbRepository = tmdbRepository;
    }

    MutableLiveData<PagingState> getPagedListStateLiveData() {
        return pagedListStateLiveData;
    }

    LiveData<PagedList<ShowModel>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    void invalidateListData() {
        showDataSourceFactory.invalidate();
    }

    void prepare(String type, String subtype, int id) {
        showDataSourceFactory = new ShowDataSourceFactory(disposable, pagedListStateLiveData, type, subtype,id, tmdbRepository);

        PagedList.Config build = new PagedList.Config.Builder().setPrefetchDistance(20).build();

        pagedListLiveData = new LivePagedListBuilder<>(showDataSourceFactory, build).build();
    }


    public ListActivityModel getActivityModel() {
        return activityModel;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
