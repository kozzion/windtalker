package com.windtalker.model;

import android.content.Context;

import java.util.Map;

/**
 * Created by jaapo on 26-10-2017.
 */

public class ServiceContact {



    private static ServiceContact sSingleton;

    public static synchronized ServiceContact getInstance() {
        if(sSingleton == null)
        {
            sSingleton = new ServiceContact();
        }
        return sSingleton;
    }

    public static synchronized ServiceContact getInstance(Context context) {
        if(sSingleton == null)
        {
            sSingleton = new ServiceContact(context);
        }
        return sSingleton;
    }




    public ServiceContact() {

    }

    public ServiceContact(Context context)
    {
        load(context);

    }

    private void load(Context context) {
    }

    public void enqueue(Map<String, String> message) {
    }
}
