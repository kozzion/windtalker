package com.windtalker.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaapo on 10-11-2017.
 */

public class DTOContact {
    public String Key;
    public String Name;
    public List<String> ChannelKeyList;

    public DTOContact(){
        this.Key = "";
        this.Name = "";
        this.ChannelKeyList = new ArrayList<>();
    }

    public DTOContact(
            String key,
            String name,
            List<String> channelKeyList) {
        Key = key;
        Name = name;
        ChannelKeyList = channelKeyList;
    }
}
