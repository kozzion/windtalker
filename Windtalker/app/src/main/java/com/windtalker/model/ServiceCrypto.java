package com.windtalker.model;

import com.windtalker.dto.DTOMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaapo on 26-10-2017.
 */

public class ServiceCrypto {

    private Map<String, String> mUserKeyToContactPublicKeyMap;
    private Map<String, String> mUserKeyToPersonalPublicKeyMap;
    private Map<String, String> mUserKeyToMixnetPublicKeyMap;

    private String mPrivateContactKey;
    private String mPrivateMixnetKey;
    private Map<String, String> mUserKeyToPersonalPrivateKeyMap;


    private static ServiceCrypto sSingleton;

    public static synchronized ServiceCrypto getInstance() {
        if(sSingleton == null)
        {
            sSingleton = new ServiceCrypto();
        }
        return sSingleton;
    }

    public Map<String,String> decryptContact(DTOMessage dtoMessage) {
        Map<String,String> map = new HashMap<>();
        map.put("Type", "KeyRequest");
        map.put("From", dtoMessage.Sender);
        map.put("Key", dtoMessage.Payload);
        return map;
    }
    public ModelMessage decryptPersonal(DTOMessage dtoMessage) {
        return new ModelMessage(dtoMessage.Sender, dtoMessage.Payload);
    }
    public Map<String,String> decryptMixnet(DTOMessage dtoMessage) {
        Map<String,String> map = new HashMap<>();
        return map;
    }
}
