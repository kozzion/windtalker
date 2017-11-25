package com.windtalker.database.preference;

import android.content.Context;

import com.windtalker.database.IDatabase;
import com.windtalker.database.IDatabaseReference;

/**
 * Created by jaapo on 26-10-2017.
 */

public class DatabaseLocalPreference implements IDatabase{

    private String mDatabaseName;


    @Override
    public IDatabaseReference getReference(Context context) {
        return new DatabaseReferenceLocalPreference(context, mDatabaseName, mDatabaseName) {
        };
    }

    @Override
    public void clear() {

    }
}
