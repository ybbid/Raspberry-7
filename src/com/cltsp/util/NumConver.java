package com.cltsp.util;

import java.math.BigInteger;

/**
 * Created by leacher on 16-8-8.
 */
public class NumConver {
    /*进制转换*/

    public static String binary(byte[] bytes, int radix){
        // TODO: 16-8-8 二进制转换成radix进制字符串
        return (new BigInteger(1,bytes)).toString(radix);
    }
    public static int HexStringToInt(String src){
        // TODO: 16-8-16  16进制字符串,转换成16进制整形
        if (0==src.indexOf("0x")){
            src=src.replaceFirst("0x","");
        }
        Integer temp=Integer.parseInt(src,16);
        return (int) temp.longValue();
    }
    public static long HexStringToLong(String src){
        // TODO: 16-8-8  16进制字符串,转换成16进制长整形
        if (0==src.indexOf("0x")){
            src=src.replaceFirst("0x","");
        }
        Long temp=Long.parseLong(src,16);
        return temp.longValue();
    }
    public static byte[] HexStringToBinary(String src){
        // TODO: 16-8-8 16进制字符串,转换成二进制数组
        int m=0,n=0;
        int len=src.length()/2;
        System.out.println(len);
        byte[] ret = new byte[len];
        for (int i = 0; i < len; i++)
        {
            m=i*2+1;
            n=m+1;
            //高位
            int h = Byte.decode("0x" + src.substring(i*2, m))<<4;
            //低位
            int l=Byte.decode("0x"+ src.substring(m,n));
            //相与组装
            ret[i]=(byte)(h|l);
        }
        return ret;
    }
}
