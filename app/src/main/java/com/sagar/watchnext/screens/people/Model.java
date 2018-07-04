package com.sagar.watchnext.screens.people;

import com.sagar.watchnext.network.models.people.Person;
import com.sagar.watchnext.network.models.people.Persons;
import com.sagar.watchnext.network.repo.TmdbPeopleRepo;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:12
 */
@PeopleFragmentScope
public class Model implements PeopleFragmentMvpContract.Model {
    private TmdbPeopleRepo peopleRepo;

    @Inject
    public Model(TmdbPeopleRepo peopleRepo) {
        this.peopleRepo = peopleRepo;
    }

    public List<Person> getPopularPeople() throws IOException {

        Persons persons = peopleRepo.getPopularPeople().execute().body();
        if (persons != null) {
            return persons.getPersons();
        }
        return null;
    }
}
