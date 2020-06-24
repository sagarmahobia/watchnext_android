package com.sagar.watchnext.activities.list.showadapter;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class ShowModel extends BaseObservable {


    private int id;

    private String imageUrl;

    private String title;
    private String rating;

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRating(String rating) {
        this.rating = rating;
        notifyPropertyChanged(BR.title);

    }

    @Bindable
    public String getRating() {
        return rating;
    }
}
