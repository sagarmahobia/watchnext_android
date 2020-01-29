package com.sagar.watchnext.views.cardrecycler;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


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
        notifyPropertyChanged(com.sagar.watchnext.BR.title);
    }

    @Bindable
    public Status getStatus() {
        return status == null ? Status.LOADING : status;
    }

    public void setStatus(Status status) {
        this.status = status;
        notifyPropertyChanged(com.sagar.watchnext.BR.status);
    }

    public enum Status {
        LOADING,
        UPDATING,
        ERROR,
        SUCCESS;
    }
}
