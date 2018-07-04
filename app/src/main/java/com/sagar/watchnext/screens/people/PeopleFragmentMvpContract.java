package com.sagar.watchnext.screens.people;

import com.sagar.watchnext.network.models.people.Person;

import java.io.IOException;
import java.util.List;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:11
 */
interface PeopleFragmentMvpContract {
    interface View {
    }

    interface Presenter {
    }

    interface Model {
        List<Person> getPopularPeople() throws IOException;
    }
}
