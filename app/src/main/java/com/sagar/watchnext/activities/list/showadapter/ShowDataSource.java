package com.sagar.watchnext.activities.list.showadapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.ItemKeyedDataSource;

import com.sagar.watchnext.network.newmodels.CardItem;
import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.response.PagingState;
import com.sagar.watchnext.utils.ImageUrlUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class ShowDataSource extends ItemKeyedDataSource<Integer, ShowModel> {

    private TMDBRepository tmdbRepository;
    private CompositeDisposable disposable;

    private MutableLiveData<PagingState> stateLiveData;

    private int page = 0;
    private String type;
    private String subtype;


    ShowDataSource(
            TMDBRepository tmdbRepository, @NonNull CompositeDisposable disposable,
            @NonNull MutableLiveData<PagingState> stateLiveData,
            String type, String subtype) {
        this.tmdbRepository = tmdbRepository;

        this.disposable = disposable;
        this.stateLiveData = stateLiveData;
        this.type = type;
        this.subtype = subtype;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<ShowModel> callback) {
        stateLiveData.postValue(PagingState.loading());
        disposable.add(tmdbRepository.getPagedList(type, subtype,1).map(result -> populateCardModels(result.getCardItems())).subscribe(feeds -> {
                    callback.onResult(feeds);
                    page = 2;
                    stateLiveData.postValue(PagingState.success());
                }, error -> {
                    stateLiveData.postValue(PagingState.error(error));
                })

        );
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<ShowModel> callback) {
        stateLiveData.postValue(PagingState.loading());
        disposable.add(tmdbRepository.getPagedList(type, subtype, page).map(result -> populateCardModels(result.getCardItems())).subscribe(feeds -> {
                    callback.onResult(feeds);
                    page++;
                    stateLiveData.postValue(PagingState.success());
                }, error -> {
                    stateLiveData.postValue(PagingState.error(error));
                })

        );
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

    private List<ShowModel> populateCardModels(List<CardItem> cardItems) {
        List<ShowModel> showModels = new ArrayList<>();
        for (CardItem cardItem : cardItems) {
            ShowModel cardModel = new ShowModel();
            cardModel.setId(cardItem.getId());
            cardModel.setImageUrl(ImageUrlUtil.getPosterImageUrl(cardItem.getPosterPath()));
            cardModel.setTitle(cardItem.getTitle());
            showModels.add(cardModel);
        }
        return showModels;
    }
}
