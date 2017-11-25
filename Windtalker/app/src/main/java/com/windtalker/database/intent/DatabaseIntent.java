package com.windtalker.database.intent;

import android.content.Context;

import com.google.gson.JsonObject;
import com.windtalker.database.IDatabase;
import com.windtalker.database.IDatabaseReference;

import java.util.List;

/**
 * Created by jaapo on 2-11-2017.
 */

public class DatabaseIntent implements IDatabase {

    public static final String FILTER_BIND   = "FILTER_BIND";
    public static final String FILTER_UNBIND = "FILTER_UNBIND";
    public static final String FILTER_CHANGE = "FILTER_CHANGE";
    public static final String FILTER_SET_VALUE = "FILTER_SET_VALUE";
    public static final String EXTRA_PATH = "EXTRA_PATH";
    public static final String EXTRA_DATA_SNAPSHOT = "EXTRA_DATA_SNAPSHOT";




    public DatabaseIntent() {
    }

    public void setValue(List<String> path, JsonObject object) {
    }

    @Override
    public IDatabaseReference getReference(Context context) {
        return null;
    }

    @Override
    public void clear() {

    }
}
