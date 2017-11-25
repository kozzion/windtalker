package com.windtalker.dto;

/**
 * Created by jaapo on 16-11-2017.
 */

public class DTOChannelHeader {

    public String Key;
    public String Name;

    public DTOChannelHeader(){
        this.Key = "";
        this.Name = "";
    }

    public DTOChannelHeader(String key,
                            String name) {
        Key = key;
        Name = name;
    }
}
