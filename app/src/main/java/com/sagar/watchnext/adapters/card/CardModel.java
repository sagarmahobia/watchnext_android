package com.sagar.watchnext.adapters.card;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 01:04
 */
public class CardModel extends BaseObservable {

    private int id;

    private String imageUrl;

    private String title;

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

}
