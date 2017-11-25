package com.windtalker.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaapo on 25-10-2017.
 */

public class ToolsMessage {
    //public static final int MAX_MESSAGE_SIZE = 2048;
    //public static final int MESSAGE_TYPE_CONTACT_REQUEST_PERSONAL_KEY = 1;
    //public static final int MESSAGE_TYPE_PERSONAL_MESSAGE = 2;

    private static final String STRING_SPLIT  = "$S";
    private static final String STRING_REVERT = "$$";
    private static final String STRING_ESCAPE = "$";

    public static Map<String, String> stringToMap(String string)
    {
        Map<String, String> map = new HashMap<>();

        int indexBeginKey = 0;
        int indexEndKey = findNextSplit(string, indexBeginKey);
        int indexBeginValue = indexEndKey + 2;
        int indexEndValue = findNextSplit(string, indexBeginValue);

        if(indexEndValue == -1) {
            indexEndValue = string.length();
        }

        while(indexEndKey != -1)
        {
            String key = string.substring(indexBeginKey, indexEndKey).replace(STRING_REVERT, STRING_ESCAPE);
            String value = string.substring(indexBeginValue, indexEndValue).replace(STRING_REVERT, STRING_ESCAPE);
            map.put(key, value);

            indexBeginKey = indexEndValue + 2;
            indexEndKey = findNextSplit(string, indexBeginKey);
            indexBeginValue = indexEndKey + 2;
            indexEndValue = findNextSplit(string, indexBeginValue);
            if(indexEndValue == -1) {
                indexEndValue = string.length();
            }
        }
        return map;
    }

    public static String mapToString(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;
        for (String key: map.keySet()) {
            if(!isFirst)
            {
                stringBuilder.append(STRING_SPLIT);
            }
            stringBuilder.append(key.replace(STRING_ESCAPE, STRING_REVERT));
            stringBuilder.append(STRING_SPLIT);
            stringBuilder.append(map.get(key).replace(STRING_ESCAPE, STRING_REVERT));
            isFirst = false;
        }
        return stringBuilder.toString();
    }

    private static int findNextSplit(String string, int startIndex)
    {
        int findIndex = string.indexOf(STRING_ESCAPE, startIndex);
        while(findIndex != -1)
        {

            if(findIndex == string.length() -1) {
                return -1;  //If this was the last char return -1;
            }else if (string.substring(findIndex, findIndex + 2).equals(STRING_REVERT)) {
                findIndex = string.indexOf(STRING_ESCAPE, findIndex + 2);
            }else if (string.substring(findIndex, findIndex + 2).equals(STRING_SPLIT)) {
                return findIndex;
            }
        }
        return -1;
    }




}
