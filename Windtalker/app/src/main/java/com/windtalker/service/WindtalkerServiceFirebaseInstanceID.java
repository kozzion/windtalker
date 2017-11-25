package com.windtalker.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.windtalker.model.ModelApplication;

import static android.content.ContentValues.TAG;

/**
 * Created by jaapo on 22-10-2017.
 */

public class WindtalkerServiceFirebaseInstanceID extends FirebaseInstanceIdService {



    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String messagingToken = FirebaseInstanceId.getInstance().getToken();

        Log.e(TAG, "Refreshed token: " + messagingToken);

        // If you want to send messages to this application instance o
        ModelApplication.getInstance(this).setToken(this, messagingToken);
    }
}
