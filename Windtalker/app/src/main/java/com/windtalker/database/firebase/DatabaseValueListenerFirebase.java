package com.windtalker.database.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.windtalker.database.IDatabaseValueListener;

/**
 * Created by jaapo on 26-10-2017.
 */

public class DatabaseValueListenerFirebase implements ValueEventListener {

    IDatabaseValueListener mListener;

    public DatabaseValueListenerFirebase(IDatabaseValueListener listener) {
        mListener = listener;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        mListener.onDataChange(new DataSnapshotFirebase(dataSnapshot));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        mListener.onCancelled(databaseError.toString());
    }
}
