package com.windtalker.database;

/**
 * Created by jaapo on 26-10-2017.
 */

public interface IDatabaseValueListener {


    void onDataChange(IDataSnapshot dataSnapshot);

    void onCancelled(String error);
}
