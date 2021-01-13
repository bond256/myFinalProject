package com.example.myfinalproj.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinalproj.R;
import com.example.myfinalproj.db.Channel;

import java.util.List;


public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ViewHolder> {
    private OnClickListener mOnClickListener;
    private List<Channel> channelsList;


    public ChannelsAdapter(List<Channel> channels, OnClickListener onClickListener) {
        channelsList = channels;
        this.mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view, (OnClickListener) mOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.getTextView().setText(channelsList.get(position).title);
    }

    @Override
    public int getItemCount() {
        return channelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameChannel;
        private OnClickListener onClickListener;

        public ViewHolder(@NonNull View itemView, OnClickListener clickListener) {
            super(itemView);
            nameChannel = itemView.findViewById(R.id.nameTitle);
            this.onClickListener = clickListener;
            itemView.setOnClickListener(this);
        }

        public TextView getTextView() {
            return nameChannel;
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(getAdapterPosition());
        }
    }

    public interface OnClickListener {
        void onClick(int position);
    }
}
