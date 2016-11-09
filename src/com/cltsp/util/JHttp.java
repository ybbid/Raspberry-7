package com.cltsp.util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by leacher on 16-8-16.
 */
public class JHttp {
    public static String sendGet(String url,String param){
        return null;
    }
    public static String sendPost(String urlstr, JSONObject json){
        URLConnection connection=null;
        URL url=null;
        try {
            url=new URL(urlstr);
            connection = url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            //System.out.println("Create Url "+urlstr+" Exception:"+e.toString()+".");
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println(urlstr+" Connection Exception.");
        }
        if (connection!=null){
            //Setting http connection attr.
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            //Setting http header message.
            connection.setRequestProperty("Accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //Send json obj.
            PrintWriter out=null;
            try {
                connection.connect();
                out=new PrintWriter(connection.getOutputStream());
                out.print(json.toString());
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Read response.
            BufferedReader in=null;
            String result="";
            try {
                in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line=in.readLine())!=null){
                    result+=line;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (out!=null){
                        out.close();
                    }
                    if (in!=null){
                        in.close();
                    }
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
            return result;
        }else {
            System.out.println("Connection is null.");
            return null;
        }
    }
}
