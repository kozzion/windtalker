package com.windtalker.database.intent;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.windtalker.database.IDataSnapshot;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jaapo on 3-11-2017.
 */

public class DataSnapshotIntent implements Parcelable, IDataSnapshot {

    JSONObject object;

    public DataSnapshotIntent(String in) {
        try {
            object = new JSONObject(in);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public DataSnapshotIntent(Parcel in) {
        try {
            object = new JSONObject(in.readString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(object.toString());
    }

    public static final Creator<DataSnapshotIntent> CREATOR = new Creator<DataSnapshotIntent>() {
        @Override
        public DataSnapshotIntent createFromParcel(Parcel in) {
            return new DataSnapshotIntent(in);
        }

        @Override
        public DataSnapshotIntent[] newArray(int size) {
            return new DataSnapshotIntent[size];
        }
    };

    @Override
    public Object getValue() {
        return object;
    }

    @Override
    public Iterable<IDataSnapshot> getChildren() {
        return null;
    }

    @Nullable
    @Override
    public <Type> Type getValue(Class<Type> classType) {
        return new Gson().fromJson("", classType);
    }


}
