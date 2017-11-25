package com.windtalker.database;

import com.google.gson.JsonElement;

import java.util.Map;

/**
 * Created by jaapo on 7-11-2017.
 */

public class ToolsJSON {

    public static String printClass(JsonElement je, String ident) {
        StringBuilder sb = null;
        if (je.isJsonNull())
            return "null";

        if (je.isJsonPrimitive()) {
            if (je.getAsJsonPrimitive().isBoolean())
                return "Boolean";
            if (je.getAsJsonPrimitive().isString())
                return "String";
            if (je.getAsJsonPrimitive().isNumber()){
                return "Number";
            }
        }

        if (je.isJsonArray()) {
            sb = new StringBuilder("array<");
            for (JsonElement e : je.getAsJsonArray()) {
                sb.append(printClass(e, ident+ "    "));
            }
            sb.append(">");
            return sb.toString();
        }

        if (je.isJsonObject()) {
            sb = new StringBuilder("map<\n");
            for (Map.Entry<String, JsonElement> e : je.getAsJsonObject().entrySet()) {
                sb.append(ident);
                sb.append(e.getKey()).append(":");
                sb.append(printClass(e.getValue(), ident+"   "));
                sb.append("\n");
            }
            sb.append(ident);
            sb.append(">");
            return sb.toString();
        }
        return "";

    }
}
