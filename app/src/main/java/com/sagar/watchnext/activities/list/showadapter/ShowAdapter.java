package com.sagar.watchnext.activities.list.showadapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.sagar.watchnext.databinding.ShowListItemBinding;

public class ShowAdapter extends PagedListAdapter<ShowModel, ShowViewHolder> {

    private static DiffUtil.ItemCallback<ShowModel> itemCallback = new DiffUtil.ItemCallback<ShowModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull ShowModel oldItem, @NonNull ShowModel newItem) {
            return oldItem.getId() == newItem.getId();//TODO
        }

        @Override
        public boolean areContentsTheSame(@NonNull ShowModel oldItem, @NonNull ShowModel newItem) {
            return oldItem.getId().equals(newItem.getId());//TODO
        }
    };
    private LayoutInflater inflater;
    private OnItemClickListener clickListener;

    public ShowAdapter() {
        super(itemCallback);
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        ShowListItemBinding binding = ShowListItemBinding.inflate(inflater, parent, false);
        return new ShowViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowViewHolder holder, int position) {
        final ShowModel model = getItem(position);

        holder.getBinding().setModel(model);
        holder.bindTo(model);
        holder.itemView.setOnClickListener(view -> {
            if (clickListener != null) {
                clickListener.onClick(model);
            }
        });
    }


    public interface OnItemClickListener {
        void onClick(ShowModel showModel);
    }
}

