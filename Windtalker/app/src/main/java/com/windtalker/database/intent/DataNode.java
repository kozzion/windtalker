package com.windtalker.database.intent;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

/**
 * Created by jaapo on 3-11-2017.
 */

public class DataNode {

    private DataNode mParent;
    private String mPath;

    private Map<String, DataNode> mChildren;

    public Map<String, JsonObject> setValue(List<String> path, JsonObject object) {
        if(path.size() ==0)
        {

        }
        return null;
    }
}
