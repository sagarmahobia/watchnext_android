package com.sagar.watchnext.screens.tv.Adapters;

import android.support.annotation.NonNull;

import com.sagar.watchnext.adapters.BaseRecyclerAdapter;
import com.sagar.watchnext.adapters.CardViewHolder;
import com.sagar.watchnext.screens.tv.TvFragmentMvpContract;
import com.sagar.watchnext.screens.tv.TvFragmentScope;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 04-Jul-18. at 20:11
 */

@TvFragmentScope
public class PopularAdapter extends BaseRecyclerAdapter {

    private TvFragmentMvpContract.Presenter presenter;

    @Inject
    protected PopularAdapter(Picasso picasso, TvFragmentMvpContract.Presenter presenter) {
        super(picasso);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int position) {
        presenter.onBindPopularShowCard(cardViewHolder, position);

        cardViewHolder.itemView.setOnClickListener(view -> {
            presenter.onPopularRecyclerItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getPopularCardsCount();

    }
}
