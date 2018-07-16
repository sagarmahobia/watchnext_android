
package com.sagar.watchnext.network.models.people;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Persons {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Person> persons;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }


    public List<Person> getPersons() {
        return persons;
    }


    public int getTotalResults() {
        return totalResults;
    }


    public int getTotalPages() {
        return totalPages;
    }

}
