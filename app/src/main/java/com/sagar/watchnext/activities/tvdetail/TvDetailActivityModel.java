package com.sagar.watchnext.activities.tvdetail;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class TvDetailActivityModel extends BaseObservable {

    private String overViewText;
    private String originalTitle;
    private String originalLanguage;
    private String statusText;
    private String releaseDate;
    private String productionCompanies;
    private String homepage;
    private String showType;
    private String firstAirDate;
    private String lastAirDate;
    private String network;

    @Bindable
    public String getOverViewText() {
        return overViewText;
    }

    public void setOverViewText(String overViewText) {
        this.overViewText = overViewText;
        notifyPropertyChanged(com.sagar.watchnext.BR.overViewText);
    }

    @Bindable
    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        notifyPropertyChanged(com.sagar.watchnext.BR.originalTitle);
    }

    @Bindable
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
        notifyPropertyChanged(com.sagar.watchnext.BR.originalLanguage);
    }

    @Bindable
    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
        notifyPropertyChanged(com.sagar.watchnext.BR.statusText);
    }

    @Bindable
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        notifyPropertyChanged(com.sagar.watchnext.BR.releaseDate);
    }

    @Bindable
    public String getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(String productionCompanies) {
        this.productionCompanies = productionCompanies;
        notifyPropertyChanged(com.sagar.watchnext.BR.productionCompanies);
    }

    @Bindable
    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
        notifyPropertyChanged(com.sagar.watchnext.BR.homepage);
    }

    @Bindable
    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
        notifyPropertyChanged(com.sagar.watchnext.BR.showType);
    }

    @Bindable
    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
        notifyPropertyChanged(com.sagar.watchnext.BR.firstAirDate);
    }

    @Bindable
    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
        notifyPropertyChanged(com.sagar.watchnext.BR.lastAirDate);
    }

    public void setNetwork(String network) {
        this.network = network;
        notifyPropertyChanged(com.sagar.watchnext.BR.network);
    }

    @Bindable
    public String getNetwork() {
        return network;
    }
}
