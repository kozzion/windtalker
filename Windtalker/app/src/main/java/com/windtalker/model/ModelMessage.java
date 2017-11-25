package com.windtalker.model;

import com.windtalker.tools.ToolsMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaapo on 25-10-2017.
 */

public class ModelMessage {

    private String mSender;
    private Map<String, String> mData;


    public ModelMessage(String sender, String messageString)
    {
        mSender = sender;
        mData = ToolsMessage.stringToMap(messageString);
    }

    public ModelMessage(String sender)
    {
        mSender = sender;
        mData = new HashMap<>();
    }

    public String toPayload()
    {
        return ToolsMessage.mapToString(mData);
    }


    public String getChatMessage() {
        return mData.get("ChatMessage");
    }

    public void setChatMessage(String chatMessage) {
        mData.put("ChatMessage", chatMessage);
    }

    public String getChannelKey() { return mData.get("ChannelKey"); }

    public void setChannelKey(String channelKey) {
        mData.put("ChannelKey", channelKey);
    }

    public String getSender() {
        return mSender;
    }
}
