package com.windtalker;

import android.app.Application;
import android.content.Intent;

import com.windtalker.database.ServiceDatabase;

/**
 * Created by jaapo on 15-10-2017.
 */

public class WindtalkerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, ServiceDatabase.class));



     }
}
