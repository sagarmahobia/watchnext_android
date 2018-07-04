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

        void onSucceedLoadingAiringTodayShowList();

        void onErrorLoadingAiringTodayShowList();

        void onSucceedLoadingOnTheAirShowList();

        void onErrorLoadingOnTheAirShowList();

        void onSucceedLoadingPopularList();

        void onErrorLoadingPopularList();

        void onSucceedLoadingTopRatedShowList();

        void onErrorLoadingTopRatedShowList();
    }

    public interface Presenter {
        void onCreate();

        void onDestroy();

        //Airing Today
        void onBindAiringTodayShowCard(Card card, int position);

        void onAiringTodayRecyclerItemClick(int position);

        int getAiringTodayCardsCount();

        //On The Air
        void onBindOnTheAirShowCard(Card card, int position);

        void onOnTheAirRecyclerItemClick(int position);

        int getOnTheAirCardsCount();

        //Popular
        void onBindPopularShowCard(Card card, int position);

        void onPopularRecyclerItemClick(int position);

        int getPopularCardsCount();

        //Top Rated

        void onBindTopRatedShowCard(Card card, int position);

        void onTopRatedRecyclerItemClick(int position);

        int getTopRatedCardsCount();
    }

    interface Model {

        List<Show> getAiringToday() throws IOException;

        List<Show> getOnTheAir() throws IOException;

        List<Show> getPopular() throws IOException;

        List<Show> getTopRated() throws IOException;
    }

}
