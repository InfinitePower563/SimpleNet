package io.github.infinitepower563.simplenet.util;

import org.json.JSONObject;

public class JsonMapper {
    public static JSONObject mapResultToJson(String str) {
        return new JSONObject(str);
    }
}
