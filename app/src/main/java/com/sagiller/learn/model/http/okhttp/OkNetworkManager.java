package com.sagiller.learn.model.http.okhttp;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.sagiller.learn.json.gson.JsonUtil;
import com.sagiller.learn.model.webpage.Webpage;
import com.sagiller.learn.model.webpage.WebpageCategory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkNetworkManager {

    public static final String WEBPAGE_URL = "url";
    public static final String WEBPAGE_NAME = "name";
    public static final String WEBPAGE_DESC = "desc";


    /**
     * API URL
     */
    public static final String SIT_BASE_API_URL = "http://172.20.10.7:8000/api/v1";
    public static final String PROD_BASE_API_URL = "http://kit.sagiller.com/api/v1";
    public static final String CURRENT_BASE_API_URL = SIT_BASE_API_URL;
    public static final String API_WEBPAGECATEGORY_MODUELS = CURRENT_BASE_API_URL + "/webpagecategory/modules";
    public static final String API_WEBPAGECATEGORY_WEBPAGES = CURRENT_BASE_API_URL + "/webpagecategory/%s/webpages";

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient okHttpClient;


    /**
     * GET webpagecategory moduels
     */
    public static List<WebpageCategory> getWebpageCategoryModuels() {
        Type type = new TypeToken<List<WebpageCategory>>() {
        }.getType();

        return get(API_WEBPAGECATEGORY_MODUELS,type);
    }

    /**
     * GET webpages
     */
    public static List<Webpage> getWebpages(int categoryId) {
        String url = String.format(API_WEBPAGECATEGORY_WEBPAGES, categoryId);
        Type type = new TypeToken<List<Webpage>>() {
        }.getType();

        return get(url,type);
    }

    /**
     * POST create webpage
     */
    public static void createWebpage(String url, String name, String desc) {
        Map<String, String> params = new HashMap<>();
        params.put(WEBPAGE_URL, url);
        params.put(WEBPAGE_NAME, name);
        params.put(WEBPAGE_DESC, desc);

        post(url, params);
    }

    /**
     * PUT update webpage
     */
    public static void updateWebpage(String url, String name, String desc) {
        Map<String, String> params = new HashMap<>();
        params.put(WEBPAGE_URL, url);
        params.put(WEBPAGE_NAME, name);
        params.put(WEBPAGE_DESC, desc);

        put(url, params);
    }

    /**
     * DELETE webpage
     */
    public static void deleteWebpage() {
        delete(API_WEBPAGECATEGORY_WEBPAGES);
    }

    private static <T> T get(String url, Type type) {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = OkNetworkManager.getOkHttpClient().newCall(request).execute();
            String res = response.body().string();
            JSONObject jsonObject = new JSONObject(res);
            return JsonUtil.getResponseFromResponseJson(jsonObject, type);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {

        }
        return null;
    }


    private static void post(String url, Map<String, String> params) {
        try {
            String json = JsonUtil.getJsonFromJavaObject(params);
            //TODO should throw Exception instead of return;
            if (TextUtils.isEmpty(json)) return;
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            OkNetworkManager.getOkHttpClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void put(String url, Map<String, String> params) {
        try {
            String json = JsonUtil.getJsonFromJavaObject(params);
            //TODO should throw Exception instead of return;
            if (TextUtils.isEmpty(json)) return;
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            OkNetworkManager.getOkHttpClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void delete(String url) {
    }


    private static void cancel() {

    }

    public static synchronized void initialize(){
        if (okHttpClient == null){
            synchronized (OkNetworkManager.class){
                if (okHttpClient == null){
                    okHttpClient = new OkHttpClient();
                    okHttpClient = new OkHttpClient.Builder()
                            .readTimeout(10, TimeUnit.SECONDS)
                            .build();
                    okHttpClient.readTimeoutMillis();
                }
            }
        }
    }


    /**获取RequestQueue实例
     * @return 返回RequestQueue实例
     */
    public static OkHttpClient getOkHttpClient(){
        if (okHttpClient == null)
            throw new RuntimeException("Please initialize mRequestQueue before using it.") ;
        return okHttpClient ;
    }
}