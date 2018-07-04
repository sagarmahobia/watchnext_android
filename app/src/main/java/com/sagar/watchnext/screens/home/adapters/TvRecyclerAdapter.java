package com.sagar.watchnext.screens.home.adapters;

import android.support.annotation.NonNull;
import android.view.View;

import com.sagar.watchnext.adapters.BaseRecyclerAdapter;
import com.sagar.watchnext.adapters.CardViewHolder;
import com.sagar.watchnext.screens.home.HomeFragmentMvpContract;
import com.sagar.watchnext.screens.home.HomeFragmentScope;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 23:41
 */

@HomeFragmentScope
public class TvRecyclerAdapter extends BaseRecyclerAdapter {
    private HomeFragmentMvpContract.Presenter presenter;

    @Inject
    TvRecyclerAdapter(HomeFragmentMvpContract.Presenter presenter, Picasso picasso) {
        super(picasso);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int i) {
        presenter.onBindTvCard(cardViewHolder, i);
        cardViewHolder.itemView.setOnClickListener(view -> {
                presenter.onTvRecyclerItemClick(i);
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getTvCardsCount();
    }
}
