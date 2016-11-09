package com.cltsp.test;

import com.cltsp.bean.Turgoscope;
import com.cltsp.util.JHttp;
import org.json.JSONObject;

/**
 * Created by leacher on 16-11-1.
 */
public class JHttpTest {
    public static void main(String[] args) {
        Turgoscope turgoscope=new Turgoscope();
        turgoscope.setMaxBp("50");
        turgoscope.setMinBp("10");
        turgoscope.setHr("99");
        JSONObject json=new JSONObject(turgoscope);
        System.out.println(json.toString());
        JHttp.sendPost("http://127.0.0.1:8080/servlet",json);
    }
}
