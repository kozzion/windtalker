package com.windtalker.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.windtalker.dto.DTOMessage;
import com.windtalker.tools.ToolsFirebase;

import java.io.IOException;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by jaapo on 25-10-2017.
 */

public class ModelApplication {

    private String mEmail;
    private String mPassword;
    private String mMessagingToken;

    private static ModelApplication sSingleton;

    public static synchronized ModelApplication getInstance(Context context) {
        if(sSingleton == null)
        {
            sSingleton = new ModelApplication(context);
        }
        return sSingleton;
    }

    private ModelApplication(Context context)
    {
        load(context);
    }

    private void load(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("ModelApplication", MODE_PRIVATE);
        mMessagingToken =  preferences.getString("mMessagingToken", "");
        mEmail = preferences.getString("mEmail", "");
        mPassword = preferences.getString("mPassword", "");
    }

    public void save(Context context) {
        SharedPreferences filterSettings = context.getSharedPreferences("ModelApplication", MODE_PRIVATE);
        SharedPreferences.Editor edit = filterSettings.edit();
        edit.clear();
        edit.putString("mMessagingToken", mMessagingToken);
        edit.putString("mEmail", mEmail);
        edit.putString("mPassword", mPassword);
        edit.commit();
    }

    public void sendPersonalMessage(String userKey, ModelMessage message) {
        Log.e(TAG, "sendMessage");
        ToolsFirebase.getPersonalInbox(userKey).push().setValue(new DTOMessage(FirebaseAuth.getInstance().getCurrentUser().getUid(), message.toPayload()));
    }

    public void setToken(Context context, String messagingToken) {
        Log.e(TAG, "Refreshed token: " + messagingToken);
        mMessagingToken = messagingToken;
        save(context);
    }


    public void saveLogin(Context context, String email, String password) {
        mEmail = email;
        mPassword = password;
        save(context);
    }

    public void logout(Context context) {
        mMessagingToken = "";
        //mEmail = "";
        mPassword = "";
        new Thread(() -> {
            try {
                FirebaseInstanceId.getInstance().deleteInstanceId();
                FirebaseAuth.getInstance().signOut();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        save(context);
    }

    public String getMessagingToken() {
        mMessagingToken = FirebaseInstanceId.getInstance().getToken();
        return FirebaseInstanceId.getInstance().getToken();//return mMessagingToken; //TODO?
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public boolean isLoggedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public void attemptLogin(String email, String password, FirebaseAuth.AuthStateListener listener)
    {
        try {
            FirebaseAuth.getInstance().addAuthStateListener(listener);
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password);
        }catch ( Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void attemptRegisterAndLogin(String email, String password , FirebaseAuth.AuthStateListener listener) {
        try {
            FirebaseAuth.getInstance().addAuthStateListener(listener);
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password);
        }catch ( Exception e) {
            throw new RuntimeException(e);
        }
    }
}
