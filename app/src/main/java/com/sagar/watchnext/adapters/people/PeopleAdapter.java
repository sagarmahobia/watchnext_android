package com.sagar.watchnext.adapters.people;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.sagar.watchnext.databinding.PeopleListItemBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PeopleAdapter extends ListAdapter<PeopleModel, PeopleViewHolder> {

    private LayoutInflater inflater;
    private OnItemClickListener clickListener;

    @Inject
    PeopleAdapter() {

        super(DIFF_CALLBACK);
    }

    @Override
    public void submitList(final List<PeopleModel> list) {
        super.submitList(list != null ? new ArrayList<>(list) : null);
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        PeopleListItemBinding binding = PeopleListItemBinding.inflate(inflater, parent, false);
        return new PeopleViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        PeopleModel peopleModel = getItem(position);

        holder.getBinding().setModel(peopleModel);
        holder.bindTo(peopleModel);

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onClick(peopleModel);
            }
        });
    }


    private static final DiffUtil.ItemCallback<PeopleModel> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<PeopleModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull PeopleModel model, @NonNull PeopleModel t1) {
            return model.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PeopleModel model, @NonNull PeopleModel t1) {
            return true;
        }
    };


    public interface OnItemClickListener {
        void onClick(PeopleModel peopleModel);
    }
}

