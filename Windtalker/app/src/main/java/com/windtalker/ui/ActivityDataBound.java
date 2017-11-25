package com.windtalker.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.windtalker.database.IDatabaseValueListener;
import com.windtalker.database.intent.DataSnapshotIntent;
import com.windtalker.database.intent.DatabaseIntent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaapo on 2-11-2017.
 */

public abstract class ActivityDataBound extends ActivityBase {

    private HashMap<String, IDatabaseValueListener> mValueListeners;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mValueListeners = new HashMap<>();
    }



    public Map<String, BroadcastReceiver> getFilterToRecieverMap() {
        Map<String, BroadcastReceiver> map = super.getFilterToRecieverMap();
        map.put(DatabaseIntent.FILTER_CHANGE, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String path = intent.getStringExtra(DatabaseIntent.EXTRA_PATH);
                if(mValueListeners.containsKey(path))
                {
                    DataSnapshotIntent snapshot = intent.getParcelableExtra(DatabaseIntent.EXTRA_DATA_SNAPSHOT);
                    mValueListeners.get(path).onDataChange(snapshot);
                }
            }
        });
        return map;
    }

    public void addValueListener(String path, IDatabaseValueListener listener) {
        mValueListeners.put(path, listener);//TODO multiple listners?
        Intent intent = new Intent();
        intent.putExtra(DatabaseIntent.EXTRA_PATH, path);
        localBroadcastSend(DatabaseIntent.FILTER_BIND, intent);
    }

}

