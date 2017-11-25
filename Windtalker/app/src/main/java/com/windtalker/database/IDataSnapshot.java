package com.windtalker.database;

import android.support.annotation.Nullable;

/**
 * Created by jaapo on 29-10-2017.
 */

public interface IDataSnapshot {

    @Nullable
    <Type> Type getValue(Class<Type> classType);

    Object getValue();

    Iterable<IDataSnapshot> getChildren();
}
