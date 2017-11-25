package com.windtalker.database;

import android.content.Context;

/**
 * Created by jaapo on 26-10-2017.
 */

public interface IDatabase {

    IDatabaseReference getReference(Context context);

    void clear();
}
