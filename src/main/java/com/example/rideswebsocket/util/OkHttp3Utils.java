package com.example.rideswebsocket.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Set;
public class OkHttp3Utils {
    private static Log log= LogFactory.getLog(OkHttp3Utils.class);

    public static String get(String url,JSONObject json){
        Set<String> set=json.keySet();
        if (set.size()!=0){
            url+="?";
            for (String k:set){
                url=url+k+"="+json.getString(k)+"&";
            }
            url=url.substring(0,url.length()-1);
        }
        log.info(url);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
//                .addHeader()
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String jsonPost(String url, JSONObject json){
        OkHttpClient okHttpClient = new OkHttpClient();

        /**
         * new FormBody.Builder()表单
         */
//        RequestBody body = new FormBody.Builder()
//                .add("键", "值")
//                .add("键", "值")
//                .build();
        /**
         * JSON
         */
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json.toString());

        /**
         * 文件
         */
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/png"), file))
//                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Test
//    public void test(){
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("a","b");
//        jsonObject.put("c","d");
//        jsonObject.put("e","f");
//        String s=get("http:www.baidu.com",jsonObject);
//        log.info(s);
//    }
}
