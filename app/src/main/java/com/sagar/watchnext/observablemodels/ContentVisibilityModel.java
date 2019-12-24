package com.sagar.watchnext.observablemodels;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ContentVisibilityModel extends BaseObservable {

    private Status status;

    public void setStatus(Status status) {
        this.status = status;
        notifyPropertyChanged(com.sagar.watchnext.BR.errorGroupVisibility);
        notifyPropertyChanged(com.sagar.watchnext.BR.progressVisibility);
        notifyPropertyChanged(com.sagar.watchnext.BR.contentVisibility);

    }

    @Bindable
    public boolean getErrorGroupVisibility() {
        return status == Status.ERROR;
    }

    @Bindable
    public boolean getProgressVisibility() {
        return status == Status.LOADING;
    }

    @Bindable
    public boolean getContentVisibility() {
        return status == Status.LOADED;
    }

    public enum Status {
        LOADING, LOADED, ERROR
    }
}
