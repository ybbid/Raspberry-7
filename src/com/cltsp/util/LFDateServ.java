package com.cltsp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by zclzc on 2016/7/23.
 */
public class LFDateServ {
    private static final long lf805_st=1262304000;
    public static String produceLfTime(){
        Date date=new Date();
        long tt=date.getTime();
        tt=date.getTime()/1000-lf805_st;
        tt-=12*3600;
        String str=Long.toHexString(tt);
        return str;
    }
    public static String parseLfTime(String hexstr){
        long time_t=NumConver.HexStringToLong(hexstr);
        time_t+=20*3600;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date=sdf.format((lf805_st+time_t)*1000);
        return date;
    }
}
