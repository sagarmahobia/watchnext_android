package com.sagar.watchnext.adapters.card;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.watchnext.databinding.CardItemLayoutBinding;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 00:50
 */
class CardViewHolder extends RecyclerView.ViewHolder {

    private final CardItemLayoutBinding binding;

    CardViewHolder(@NonNull CardItemLayoutBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    CardItemLayoutBinding getBinding() {
        return binding;
    }
}
