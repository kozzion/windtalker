package com.windtalker.database.firebase;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.windtalker.database.IDatabase;
import com.windtalker.database.IDatabaseReference;

/**
 * Created by jaapo on 26-10-2017.
 */

public class DatabaseFirebase implements IDatabase {

    DatabaseReference mDatabaseReference;

    public DatabaseFirebase(DatabaseReference databaseReference)
    {
        mDatabaseReference = databaseReference;
    }

    @Override
    public IDatabaseReference getReference(Context context) {
        return new DatabaseReferenceFirebase(mDatabaseReference);
    }

    @Override
    public void clear() {
        mDatabaseReference.removeValue();
    }
}
