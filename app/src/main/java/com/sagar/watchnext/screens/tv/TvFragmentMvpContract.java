package com.sagar.watchnext.screens.tv;

import com.sagar.watchnext.adapters.Card;
import com.sagar.watchnext.network.models.tv.Show;

import java.io.IOException;
import java.util.List;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 10:14
 */
public class TvFragmentMvpContract {
    interface View {
        void showToast(String s);

        void onSucceedLoadingShowList(ListType listType);

        void onErrorLoadingShowList(ListType listType);
    }

    public interface Presenter {
        void onCreate();

        void onDestroy();

        //combined
        void onBindCard(ListType listType, Card card, int position);

        void onRecyclerItemClick(ListType listType, int position);

        int getCardsCount(ListType listType);
    }

    interface Model {

        List<Show> getAiringToday() throws IOException;

        List<Show> getOnTheAir() throws IOException;

        List<Show> getPopular() throws IOException;

        List<Show> getTopRated() throws IOException;
    }

}
