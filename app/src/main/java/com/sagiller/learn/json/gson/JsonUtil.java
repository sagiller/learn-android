package com.sagiller.learn.json.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Date;

public class JsonUtil {

    private static Gson gson = null;

    public static Gson getGsonInstance() {

        synchronized (JsonUtil.class) {
            if (gson == null) {
                GsonBuilder builder = new GsonBuilder();
                builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .registerTypeAdapter(Date.class, new DateDeserializer())
                        .registerTypeAdapterFactory(TypeFactoryUtil.getArticleBodyElementTypeFactory() );
                gson = builder.create();
            }
            return gson;
        }
    }

    public static <T> T getResponseFromResponseJson(JSONObject jsonObject, Type type) {

        try {
            String data = jsonObject.getString("data");
            if (data != null) {
                return getGsonInstance().fromJson(data.toString(), type);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static <T> T getObjectFromJson(String mStr, Class<T> toJsonClass) {

        try {

            return getGsonInstance().fromJson(mStr, toJsonClass);

        } catch (Exception e) {
        }

        return null;
    }

    public static <T> T getObjectFromJson(InputStream is, Class<T> toJsonClass) {
        try {
            Reader reader = new InputStreamReader(is);
            return getGsonInstance().fromJson(reader, toJsonClass);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> String getJsonFromJavaObject(T src) {

        try {

            return getGsonInstance().toJson(src);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
