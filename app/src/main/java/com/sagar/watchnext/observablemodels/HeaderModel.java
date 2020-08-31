package com.sagar.watchnext.observablemodels;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class HeaderModel extends BaseObservable {

    private String backDropImageUrl;

    private String posterImageUrl;

    private String title;

    private String year;

    private String runTime;

    private String genres;

    private float vote;
    private int voteCount;


    @Bindable
    public String getBackDropImageUrl() {
        return backDropImageUrl;
    }

    @Bindable
    public String getPosterImageUrl() {
        return posterImageUrl;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public String getYear() {
        return year;
    }

    @Bindable
    public String getRunTime() {
        return runTime;
    }

    @Bindable
    public String getGenres() {
        return genres;
    }

    public void setBackDropImageUrl(String backDropImageUrl) {
        this.backDropImageUrl = backDropImageUrl;
        notifyPropertyChanged(BR.backDropImageUrl);
    }

    public void setPosterImageUrl(String posterImageUrl) {
        this.posterImageUrl = posterImageUrl;
        notifyPropertyChanged(BR.posterImageUrl);
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);

    }

    public void setYear(String year) {
        this.year = year;
        notifyPropertyChanged(BR.year);
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
        notifyPropertyChanged(BR.runTime);
    }

    public void setGenres(String genres) {
        this.genres = genres;
        notifyPropertyChanged(BR.genres);
    }


    @Bindable
    public String getVote() {
        return String.valueOf(vote);
    }


    @Bindable
    public int getVoteCount() {
        return voteCount;
    }

    public void setVote(float vote) {
        this.vote = vote;
        notifyPropertyChanged(com.sagar.watchnext.BR.vote);
    }

    public void setVoteCount(float voteCount) {
        this.voteCount = (int) voteCount;
        notifyPropertyChanged(com.sagar.watchnext.BR.voteCount);
    }

}
