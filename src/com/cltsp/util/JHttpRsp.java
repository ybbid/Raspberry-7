package com.cltsp.util;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by leacher on 16-8-16.
 */
public class JHttpRsp {
    public static void main(String[] args) throws Exception {
        URL serverUrl=new URL("");
        HttpURLConnection con=(HttpURLConnection)serverUrl.openConnection();
        con.setRequestProperty("Content-Type","application/json");//设定请求格式json
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) ...");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestMethod("POST");
        int respCode=con.getResponseCode();
        //请求失败将数据保存

    }
}
