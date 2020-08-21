package com.sagar.watchnext.adapters.card;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.sagar.watchnext.R;
import com.sagar.watchnext.databinding.CardItemLayoutBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 00:49
 */

public class CardAdapter extends ListAdapter<CardModel, CardViewHolder> {
    private LayoutInflater layoutInflater;
    private AdapterListener adapterListener;

    @Inject
    CardAdapter() {
        super(DIFF_CALLBACK);
    }

    public void setAdapterListener(AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    @Override
    public void submitList(final List<CardModel> list) {
        super.submitList(list != null ? new ArrayList<>(list) : null);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        CardItemLayoutBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.card_item_layout, viewGroup, false);
        return new CardViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardModel item = getItem(position);
        holder.getBinding().setModel(item);
        holder.itemView.setOnClickListener(v -> {
            if (adapterListener != null) {
                adapterListener.onCardClicked(item);
            }
        });
    }

    private static final DiffUtil.ItemCallback<CardModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CardModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull CardModel model, @NonNull CardModel t1) {
                    return model.getId() == t1.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull CardModel model, @NonNull CardModel t1) {
                    return true;
                }
            };

    public interface AdapterListener {
        void onCardClicked(CardModel model);
    }
}
