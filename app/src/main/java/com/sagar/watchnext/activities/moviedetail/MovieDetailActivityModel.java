package com.sagar.watchnext.activities.moviedetail;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class MovieDetailActivityModel extends BaseObservable {

    private String overViewText;

    private String originalTitle;

    private String originalLanguage;

    private String statusText;

    private String releaseDate;

    private String budget;

    private String revenue;

    private String productionCompanies;

    private String homepage;

    @Bindable
    public String getOverViewText() {
        return overViewText;
    }

    void setOverViewText(String overViewText) {
        this.overViewText = overViewText;
        notifyPropertyChanged(com.sagar.watchnext.BR.overViewText);
    }

    @Bindable
    public String getOriginalTitle() {
        return originalTitle;
    }

    void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        notifyPropertyChanged(com.sagar.watchnext.BR.originalTitle);
    }

    @Bindable
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
        notifyPropertyChanged(com.sagar.watchnext.BR.originalLanguage);

    }

    @Bindable
    public String getStatusText() {
        return statusText;

    }

    void setStatusText(String statusText) {
        this.statusText = statusText;
        notifyPropertyChanged(com.sagar.watchnext.BR.statusText);
    }

    @Bindable
    public String getReleaseDate() {
        return releaseDate;
    }

    void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        notifyPropertyChanged(com.sagar.watchnext.BR.releaseDate);
    }

    @Bindable
    public String getBudget() {
        return budget;
    }

    void setBudget(String budget) {
        this.budget = budget;
        notifyPropertyChanged(com.sagar.watchnext.BR.budget);
    }

    @Bindable
    public String getRevenue() {
        return revenue;
    }

    void setRevenue(String revenue) {
        this.revenue = revenue;
        notifyPropertyChanged(com.sagar.watchnext.BR.revenue);
    }

    @Bindable
    public String getProductionCompanies() {
        return productionCompanies;
    }

    void setProductionCompanies(String productionCompanies) {
        this.productionCompanies = productionCompanies;
        notifyPropertyChanged(com.sagar.watchnext.BR.productionCompanies);
    }

    @Bindable
    public String getHomepage() {
        return homepage;
    }

    void setHomepage(String homepage) {
        this.homepage = homepage;
        notifyPropertyChanged(com.sagar.watchnext.BR.homepage);
    }

}
