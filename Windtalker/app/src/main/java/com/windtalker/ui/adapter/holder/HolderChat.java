package com.windtalker.ui.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.windtalker.R;
import com.windtalker.model.ModelMessage;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Dmytro Gorodnytskyi
 *         on 03-Feb-17.
 */

public class HolderChat extends RecyclerView.ViewHolder {


    @BindView(R.id.item_chat_text_sender)
    TextView mTextSender;
    @BindView(R.id.item_chat_text_chat_message)
    TextView mTextChatMessage;
    @BindView(R.id.item_chat_text_time)
    TextView mTextTime;
    @BindView(R.id.item_chat_text_status)
    TextView mTextStatus;

    private View mRootView;

    public HolderChat(@NonNull View rootView) {
        super(rootView);
        ButterKnife.bind(this, rootView);
        this.mRootView = rootView;
    }

    public void bind(@NonNull final ModelMessage message) {
        mTextSender.setText(message.getSender());
        mTextChatMessage.setText(message.getChatMessage());
        //rootView.setOnClickListener(v -> HouseDetailsActivity.start(rootView.getContext(), house));


    }
}
