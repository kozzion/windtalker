package com.windtalker.model;

import android.util.Log;

import com.windtalker.database.IDatabase;
import com.windtalker.database.IDatabaseReference;
import com.windtalker.database.IDatabaseValueListener;
import com.windtalker.database.IDataSnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by jaapo on 28-10-2017.
 */

public class ModelChannel implements IDatabaseValueListener{

    String mChannelKey;
    IDatabaseReference mReference;

    List<ModelMessage> mMessageList;



    HashSet<ListenerChannel> mListenerSet;

    public ModelChannel(IDatabase database, String channelKey) {
        mMessageList = new ArrayList<>();
        //mReference = database.getReference().child("ChannelSet").child(channelKey);
        mChannelKey = channelKey;
        //mReference.addValueEventListener(this);
        mListenerSet = new HashSet<>();

    }


    @Override
    public void onDataChange(IDataSnapshot valueNode) {
        //dataSnapshot.getValue("ChannelType");
        //Map<S>dataSnapshot.getChildMap();
    }

    @Override
    public void onCancelled(String error) {

    }

    public void process(ModelMessage message)
    {
        Log.e("ModelChannel", "mChannelKey:" + mChannelKey);
        mMessageList.add(message);
        updateListenerSet();
    }

    public List<ModelMessage> getMessageList() {
        return new ArrayList<>(mMessageList);
    }

    private void updateListenerSet() {
        for (ListenerChannel listener : mListenerSet) {
            listener.updateChannel(this);
        }
    }

    public void addListenerChannel(ListenerChannel listener)
    {
        mListenerSet.add(listener);
    }


    public interface ListenerChannel
    {
        void updateChannel(ModelChannel channel);
    }

}
