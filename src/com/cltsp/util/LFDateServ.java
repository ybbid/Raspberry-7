package com.cltsp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by zclzc on 2016/7/23.
 */
public class LFDateServ {
    private static final long lf805_st=1262304000;
    public static void main(String[] args) {
        Date date1=null;
        Long tt = null;
        long time_t=0xc491a8d;
        time_t+=20*3600;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date=sdf.format((lf805_st+time_t)*1000);
        System.out.println(date);
        try {
            date1=sdf.parse("2016/07/14 10:51:57");
            tt=date1.getTime()/1000-lf805_st;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String str=Long.toHexString(tt);
        System.out.println(date1+" "+str);
    }
}
