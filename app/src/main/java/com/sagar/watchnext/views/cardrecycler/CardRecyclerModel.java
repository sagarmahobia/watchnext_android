package com.sagar.watchnext.views.cardrecycler;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.sagar.watchnext.BR;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 18:12
 */
public class CardRecyclerModel extends BaseObservable {

    private String title;
    private Status status;

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public Status getStatus() {
        return status == null ? Status.LOADING : status;
    }

    public void setStatus(Status status) {
        this.status = status;
        notifyPropertyChanged(BR.status);
    }

    public enum Status {
        LOADING,
        UPDATING,
        ERROR,
        SUCCESS;
    }
}
