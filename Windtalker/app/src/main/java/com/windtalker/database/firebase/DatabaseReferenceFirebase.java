package com.windtalker.database.firebase;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.windtalker.database.IDatabaseReference;
import com.windtalker.database.IDatabaseValueListener;

/**
 * Created by jaapo on 26-10-2017.
 */

class DatabaseReferenceFirebase implements IDatabaseReference {

    DatabaseReference mDatabaseReference;

    public DatabaseReferenceFirebase(DatabaseReference databaseReference) {
        mDatabaseReference = databaseReference;
    }

    @Override
    public void removeValue()
    {
        mDatabaseReference.removeValue();
    }

    @Override
    public void setValue(Context context, Object object)
    {
        mDatabaseReference.setValue(object);
    }

    @Override
    public IDatabaseReference child(String childKey)
    {
        return new DatabaseReferenceFirebase(mDatabaseReference.child(childKey));
    }

    @Override
    public IDatabaseReference getParent()
    {
        return new DatabaseReferenceFirebase(mDatabaseReference.getParent());
    }

    @Override
    public void addValueEventListener(Context context, IDatabaseValueListener listener) {
        mDatabaseReference.addValueEventListener(new DatabaseValueListenerFirebase(listener));
    }

    @Override
    public void addListenerForSingleValueEvent(Context context, IDatabaseValueListener listener) {
        mDatabaseReference.addListenerForSingleValueEvent(new DatabaseValueListenerFirebase(listener));
    }

    @Override
    public void log() {

    }

    @Override
    public IDatabaseReference push() {
        return new DatabaseReferenceFirebase(mDatabaseReference.push());
    }


}
