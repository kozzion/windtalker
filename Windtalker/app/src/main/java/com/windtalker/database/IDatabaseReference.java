package com.windtalker.database;

import android.content.Context;

/**
 * Created by jaapo on 26-10-2017.
 */

public interface IDatabaseReference {

    void removeValue();

    void setValue(Context context, Object object);

    IDatabaseReference child(String childKey);

    IDatabaseReference getParent();


    void addValueEventListener(Context context, IDatabaseValueListener listener);

    void addListenerForSingleValueEvent(Context context, IDatabaseValueListener listener);

    void log();

    IDatabaseReference push();
}
