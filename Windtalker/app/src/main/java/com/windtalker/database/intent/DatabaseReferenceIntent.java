package com.windtalker.database.intent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.windtalker.database.IDatabaseReference;
import com.windtalker.database.IDatabaseValueListener;

/**
 * Created by jaapo on 2-11-2017.
 */

public class DatabaseReferenceIntent implements IDatabaseReference {

    DatabaseIntent mDatabase;

    DatabaseReferenceIntent mParent;
    String mChildKey;


    public DatabaseReferenceIntent(DatabaseIntent database) {
        mDatabase = database;
    }

    public DatabaseReferenceIntent(DatabaseReferenceIntent mParent, String mChildKey)
    {

    }


    @Override
    public void removeValue() {

    }

    @Override
    public void setValue(Context context, Object object) {
        Intent intent = new Intent("SetValue");
        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(object);
        intent.putExtra("ExtraStringJSON", jsonString);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public IDatabaseReference child(String childKey) {
        return new DatabaseReferenceIntent(this, mChildKey);
    }

    @Override
    public IDatabaseReference getParent() {
        return null;
    }

    @Override
    public void addValueEventListener(Context context, IDatabaseValueListener listener) {
        Intent intent = new Intent("AddValueEventListener");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public void addListenerForSingleValueEvent(Context context, IDatabaseValueListener listener) {

    }

    @Override
    public void log() {

    }

    @Override
    public IDatabaseReference push() {
        return null;
    }


}
