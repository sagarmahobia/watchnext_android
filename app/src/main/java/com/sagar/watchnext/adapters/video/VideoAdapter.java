package com.sagar.watchnext.adapters.video;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.watchnext.databinding.VideoListItemBinding;

import java.util.List;

import javax.inject.Inject;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private LayoutInflater inflater;
    private List<VideoModel> videoModels;
    private OnItemClickListener clickListener;

    @Inject
    VideoAdapter() {
    }

    public void setVideoModels(List<VideoModel> videoModels) {
        this.videoModels = videoModels;
        notifyDataSetChanged();
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        VideoListItemBinding binding = VideoListItemBinding.inflate(inflater, parent, false);

        return new VideoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoModel videoModel = videoModels.get(position);

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onClick(videoModel);
            }
        });
        holder.getBinding().setModel(videoModel);
        holder.bindTo(videoModel);

    }

    @Override
    public int getItemCount() {
        return videoModels != null ? videoModels.size() : 0;
    }

    public interface OnItemClickListener {
        void onClick(VideoModel videoModel);
    }
}

