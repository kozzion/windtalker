package com.windtalker.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.windtalker.R;
import com.windtalker.model.ModelMessage;
import com.windtalker.ui.adapter.holder.HolderChat;

import java.util.ArrayList;
import java.util.List;



/**
 * @author Dmytro Gorodnytskyi
 *         on 03-Feb-17.
 */

public class AdapterChannelChat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ModelMessage> data;

    public AdapterChannelChat() {
        this.data = new ArrayList<>();
    }

    public void setData(@Nullable List<ModelMessage> newData) {
        data.clear();

        if (newData != null && !newData.isEmpty()) {
            data.addAll(newData);
        }

        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new HolderChat(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HolderChat) holder).bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
