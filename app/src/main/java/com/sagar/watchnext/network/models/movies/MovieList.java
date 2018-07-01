
package com.sagar.watchnext.network.models.movies;

import android.util.Log;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MovieList {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Movie> movies;

    @SerializedName("dates")
    private Dates dates;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_results")
    private int totalResults;

    public int getPage() {
        return page;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Dates getDates() {
        return dates;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
