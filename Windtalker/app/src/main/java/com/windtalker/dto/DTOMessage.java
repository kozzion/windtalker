package com.windtalker.dto;

/**
 * Created by jaapo on 15-10-2017.
 */

public class DTOMessage {

    public String Sender;
    public String Payload;

    public DTOMessage(){
        this.Sender = "";
        this.Payload = "";
    }

    public DTOMessage(String sender,
                      String payload) {
        Sender = sender;
        Payload = payload;
    }
}
