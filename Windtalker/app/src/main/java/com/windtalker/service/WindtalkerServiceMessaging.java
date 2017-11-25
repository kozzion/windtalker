package com.windtalker.service;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.windtalker.dto.DTOMessage;
import com.windtalker.model.ServiceContact;
import com.windtalker.model.ModelMessage;
import com.windtalker.model.ServiceCrypto;
import com.windtalker.tools.ToolsFirebase;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by jaapo on 25-10-2017.
 */

public class WindtalkerServiceMessaging  {

    //Map<String, List<ModelMessage>> mTopicToMessageMap;

    //Each of these has different keys
    BlockingQueue<DTOMessage> mContactMessageQueue;
    BlockingQueue<DTOMessage> mPersonalMessageQueue;
    BlockingQueue<DTOMessage> mMixnetMessageQueue;

    public WindtalkerServiceMessaging()
    {
        //mTopicToMessageMap = new HashMap<>();
        mContactMessageQueue = new LinkedBlockingDeque<>();
        mPersonalMessageQueue = new LinkedBlockingDeque<>();
        mMixnetMessageQueue = new LinkedBlockingDeque<>();
        load();
    }

    private void load() {
    }

    public void start()
    {
        new Thread(() -> personalLoop()).start();
        new Thread(() -> contactLoop()).start();


        ToolsFirebase.getContactInbox().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Change", "onDataChange Contact: " + dataSnapshot.getChildrenCount());

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.e("Change", "onDataChange Contact: " + child);
                    mPersonalMessageQueue.add(child.getValue(DTOMessage.class));
                    ToolsFirebase.getContactInbox().child(child.getKey()).removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Change", "onCancelled Contact");
            }
        });


        ToolsFirebase.getPersonalInbox().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Change", "onDataChange Personal: " + dataSnapshot.getChildrenCount());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    mPersonalMessageQueue.add(child.getValue(DTOMessage.class));
                    ToolsFirebase.getPersonalInbox().child(child.getKey()).removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Change", "onCancelled Personal");
            }
        });

        ToolsFirebase.getMixNetInbox().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Change", "onDataChange Mixnet: " + dataSnapshot.getChildrenCount());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    mMixnetMessageQueue.add(child.getValue(DTOMessage.class));
                    ToolsFirebase.getMixNetInbox().child(child.getKey()).removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Change", "onCancelled Mixnet");
            }
        });
    }

    public void personalLoop()
    {
        while(true) {
            try {
                ModelMessage message = ServiceCrypto.getInstance().decryptPersonal(mPersonalMessageQueue.take());
                WindtalkerServiceChannel.getInstance().process(message);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void contactLoop()
    {
        while(true) {
            try {
                Map<String,String> result = ServiceCrypto.getInstance().decryptContact(mContactMessageQueue.take());
                ServiceContact.getInstance().enqueue(result);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
