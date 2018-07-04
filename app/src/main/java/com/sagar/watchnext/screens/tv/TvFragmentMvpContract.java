package com.sagar.watchnext.screens.tv;

import com.sagar.watchnext.network.models.tv.Show;
import com.sagar.watchnext.network.models.tv.Shows;

import java.io.IOException;
import java.util.List;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:14
 */
class TvFragmentMvpContract {
    interface View {
    }

    interface Presenter {
    }

    interface Model {

        List<Show> getTopRated() throws IOException;

        List<Show> getPopular() throws IOException;

        List<Show> getOnTheAir() throws IOException;

        List<Show> getAiringToday() throws IOException;
    }

}
