package com.windtalker.service;

import android.util.Log;

import com.windtalker.model.ModelChannel;
import com.windtalker.model.ModelMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaapo on 28-10-2017.
 */

public class WindtalkerServiceChannel {

    private Map<String, ModelChannel> mChannelContentMap;

    private static WindtalkerServiceChannel sSingleton;

    public static synchronized WindtalkerServiceChannel getInstance() {
        if(sSingleton == null) {
            sSingleton = new WindtalkerServiceChannel();
        }
        return sSingleton;
    }

    private WindtalkerServiceChannel()
    {
        mChannelContentMap = new HashMap<>();
    }

    public void process(ModelMessage message) {
        Log.e("WindtalkerServiceChannl", "process");
        String channelKey = message.getChannelKey();
        Log.e("WindtalkerServiceChannl", "" + channelKey);
        if(!mChannelContentMap.containsKey(channelKey))
        {
            mChannelContentMap.put(channelKey, new ModelChannel(null, channelKey));
        }
        mChannelContentMap.get(channelKey).process(message);
    }

    public ModelChannel getChannel(String channelKey) {
        if(!mChannelContentMap.containsKey(channelKey))
        {
            mChannelContentMap.put(channelKey, new ModelChannel(null, channelKey));
        }
        return mChannelContentMap.get(channelKey);
    }


}
