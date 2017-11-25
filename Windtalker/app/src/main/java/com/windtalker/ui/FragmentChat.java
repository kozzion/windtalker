package com.windtalker.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.windtalker.R;
import com.windtalker.model.ModelApplication;
import com.windtalker.model.ModelChannel;
import com.windtalker.model.ModelMessage;
import com.windtalker.service.WindtalkerServiceChannel;
import com.windtalker.service.WindtalkerServiceMessaging;
import com.windtalker.ui.adapter.AdapterChannelChat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jaapo on 11-11-2017.
 */

public class FragmentChat extends Fragment implements ModelChannel.ListenerChannel {

    @BindView(R.id.interact_recycler_chat)
    RecyclerView mRecyclerChat;
    @BindView(R.id.interact_edit_message)
    EditText mEditMessage;
    @BindView(R.id.interact_button_send)
    Button mButtonSend;

    WindtalkerServiceMessaging serviceMessaging;

    private String mContactKey;
    private AdapterChannelChat mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mButtonSend.setOnClickListener(b -> executeChat());
        serviceMessaging = new WindtalkerServiceMessaging();
        serviceMessaging.start();
        WindtalkerServiceChannel.getInstance().getChannel("Default").addListenerChannel(this);

        mAdapter = new AdapterChannelChat();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerChat.setLayoutManager(layoutManager);
        mRecyclerChat.setAdapter(mAdapter);
    }

    public void executeChat()
    {

        ModelMessage message = new ModelMessage(FirebaseAuth.getInstance().getUid());
        message.setChannelKey("Default");
        message.setChatMessage(mEditMessage.getText().toString());
        mEditMessage.setText("");
        if(!message.equals(""))
        {
            ModelApplication.getInstance(getContext()).sendPersonalMessage(FirebaseAuth.getInstance().getUid(), message);
        }
    }


    @Override
    public void updateChannel(ModelChannel channel) {

    }
}
