package com.windtalker.database;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.windtalker.database.intent.DatabaseIntent;
import com.windtalker.database.preference.DatabaseLocalPreference;
import com.windtalker.service.ServiceBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaapo on 2-11-2017.
 */

public class ServiceDatabase extends ServiceBase {

    IDatabase mDatabase;
    Map<String,Integer> mBindCountMap;

    private static ServiceDatabase sSingleton;

    public static ServiceDatabase getInstance()
    {
        if(sSingleton == null)
        {
            throw new RuntimeException("ISNUL");
        }
        return sSingleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabase = new DatabaseLocalPreference();
        mBindCountMap = new HashMap<>();
        sSingleton = this;
        test();
    }

    public void test()
    {
        Log.e("test", "teststart: ");
        //FirebaseDatabase.getInstance().getReference().setValue("Boem");
        /*FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.e("test", "test0: " + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        mDatabase.getReference(this).log();
        mDatabase.getReference(this).setValue(this, "Boem");
        mDatabase.getReference(this).addValueEventListener(this, new IDatabaseValueListener() {
            @Override
            public void onDataChange(IDataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.e("test", "test1: " + value);
            }

            @Override
            public void onCancelled(String error) {

            }
        });
    }

    @Override
    public void localBroadcastRecieve(Context context, String filter, Intent intent) {

    }

    @Override
    public Map<String, BroadcastReceiver> getFilterToRecieverMap()
    {
        Map<String, BroadcastReceiver> map = super.getFilterToRecieverMap();
        map.put(DatabaseIntent.FILTER_BIND, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String path = intent.getStringExtra(DatabaseIntent.EXTRA_PATH);

                //Update current value
                String snapshot = "";
                //if(mDatabase.containsKey(path)) {
                //    snapshot = mDatabase.get(path);
               // }
                Intent newIntent = new Intent();
                newIntent.putExtra(DatabaseIntent.EXTRA_PATH, path);
                newIntent.putExtra(DatabaseIntent.EXTRA_DATA_SNAPSHOT, snapshot);
                localBroadcastSend(DatabaseIntent.FILTER_CHANGE, intent);

                //Add listener
                if(!mBindCountMap.containsKey(path))
                {
                    mBindCountMap.put(path, 0);
                }
                mBindCountMap.put(path, mBindCountMap.get(path) + 1);
            }
        });

        map.put(DatabaseIntent.FILTER_UNBIND, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String path = intent.getStringExtra(DatabaseIntent.EXTRA_PATH);

                //Add listener
                if(mBindCountMap.get(path) == 1){
                    mBindCountMap.remove(path);
                }
                else {
                    mBindCountMap.put(path, mBindCountMap.get(path) - 1);
                }
            }
        });

        map.put(DatabaseIntent.FILTER_SET_VALUE, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String path = intent.getStringExtra(DatabaseIntent.EXTRA_PATH);
                String value = intent.getStringExtra(DatabaseIntent.EXTRA_DATA_SNAPSHOT);
                //mDatabase.put(path,value);
                //Save
                //Inform Listeners
               // for (String bindPath: mBindCountMap.values()) {
//
  //              }

            }
        });
        //TODO
        return new HashMap<>();
    }
}
