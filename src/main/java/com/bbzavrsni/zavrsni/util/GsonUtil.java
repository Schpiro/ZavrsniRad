package com.bbzavrsni.zavrsni.util;

import com.google.gson.Gson;

public final class GsonUtil {
    private static final Gson gson = new Gson();

    public static String toJson(Object object){
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> type){
        return gson.fromJson(json, type);
    }
}
