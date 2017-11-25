package com.windtalker.database.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.windtalker.database.IDatabaseReference;
import com.windtalker.database.IDatabaseValueListener;

import java.util.HashMap;

/**
 * Created by jaapo on 7-11-2017.
 */

class DatabaseReferenceLocalPreference implements IDatabaseReference {

    String mDatabaseName;
    public static final String VALUES = "Values";
    public static final String TYPES = "Types";
    String mPath;

    DatabaseLocalPreference database;
    HashMap<String, DatabaseReferenceLocalPreference> mChildMap;
    DatabaseReferenceLocalPreference parent;
    String key;

    public DatabaseReferenceLocalPreference(Context context, String databaseName, String path)
    {
        mDatabaseName = databaseName;
        mPath = path;
        //TODO load data
    }

    @Override
    public void removeValue() {

    }

    @Override
    public void setValue(Context context, Object object) {
        setValueSilent(context, object);
        //TODO emit to listeners
        Log.e("DBLP","setValue: done");
    }

    private void setValueSilent(Context context, Object object) {
        Log.e("DBLP","setValue: start");
        JsonElement element = new Gson().toJsonTree(object);

        SharedPreferences settings = context.getSharedPreferences(mDatabaseName, 0);
        SharedPreferences.Editor edit = settings.edit();

        if(element.isJsonPrimitive()) {

            Log.e("DBLP", "setValue path: " + VALUES + "\\" + mPath + " string: " + element.getAsString());
            edit.putString(VALUES + "\\" + mPath, element.getAsJsonPrimitive().toString());
            edit.apply();
        } else if(element.isJsonArray()) {
            for (int i = 0; i < element.getAsJsonArray().size(); i++) {
                if(!mChildMap.containsKey(Integer.toString(i)))
                {
                    mChildMap.put(Integer.toString(i), new DatabaseReferenceLocalPreference(context, mDatabaseName, mPath + "\\" + Integer.toString(i)));
                }
                mChildMap.get(Integer.toString(i)).setValueSilent(context, element.getAsJsonArray().get(i));
            }
        } else if (element.isJsonObject()) {
            for (String key : element.getAsJsonObject().keySet()) {
                if(!mChildMap.containsKey(key))
                {
                    mChildMap.put(key, new DatabaseReferenceLocalPreference(context, mDatabaseName, mPath + "\\" + key));
                }
                mChildMap.get(key).setValueSilent(context, element.getAsJsonObject().get(key));
            }
        } else if (element.isJsonNull()){
            Log.e("DBLP", "setValue path: " + VALUES + "\\" + mPath + " string: null");
            //TODO remove this node

        }
        Log.e("DBLP","setValue: done");
    }

    @Override
    public IDatabaseReference child(String childKey) {
        return null;
    }

    @Override
    public IDatabaseReference getParent() {
        return null;
    }

    @Override
    public void addValueEventListener(Context context, IDatabaseValueListener listener) {
        Log.e("DBLP","addValueEventListener");
    }

    @Override
    public void addListenerForSingleValueEvent(Context context, IDatabaseValueListener listener) {
        Log.e("DBLP","addListenerForSingleValueEvent");

    }

    @Override
    public void log() {

    }

    @Override
    public IDatabaseReference push() {
        return null;
    }
}
