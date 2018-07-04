package com.sagar.watchnext.screens.tv.Adapters;

import android.support.annotation.NonNull;

import com.sagar.watchnext.adapters.BaseRecyclerAdapter;
import com.sagar.watchnext.adapters.CardViewHolder;
import com.sagar.watchnext.screens.tv.TvFragmentMvpContract;
import com.sagar.watchnext.screens.tv.TvFragmentScope;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 04-Jul-18. at 20:10
 */
@TvFragmentScope
public class OnTheAirAdapter extends BaseRecyclerAdapter {

    private TvFragmentMvpContract.Presenter presenter;

    @Inject
    protected OnTheAirAdapter(Picasso picasso, TvFragmentMvpContract.Presenter presenter) {
        super(picasso);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int position) {
        presenter.onBindOnTheAirShowCard(cardViewHolder, position);

        cardViewHolder.itemView.setOnClickListener(view -> {
            presenter.onOnTheAirRecyclerItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getOnTheAirCardsCount();

    }
}
