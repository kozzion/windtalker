package com.windtalker.database.firebase;

import android.support.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.windtalker.database.IDataSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaapo on 10-11-2017.
 */

public class DataSnapshotFirebase implements IDataSnapshot{

    DataSnapshot mDataSnapshot;

    public DataSnapshotFirebase(DataSnapshot dataSnapshot) {
        mDataSnapshot = dataSnapshot;
    }

    @Nullable
    @Override
    public <Type> Type getValue(Class<Type> classType) {
        return mDataSnapshot.getValue(classType);
    }

    @Override
    public Object getValue() {
        return mDataSnapshot.getValue();
    }

    @Override
    public Iterable<IDataSnapshot> getChildren() {
        List<IDataSnapshot> data = new ArrayList<>();
        for (DataSnapshot child: mDataSnapshot.getChildren())
        {
            data.add(new DataSnapshotFirebase(child));
        }
        return data;
    }
}
