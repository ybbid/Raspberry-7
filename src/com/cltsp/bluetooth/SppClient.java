package com.cltsp.bluetooth;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by leacher on 16-8-3.
 */
public class SppClient {

    public static void main(String[] args) throws IOException {
        String serverURL;
        String[] searchArgs=null;
        ServicesSearch.main(null);
        if (ServicesSearch.serviceFound.size()==0){
            System.out.println("SSP service not found");
            return;
        }
        serverURL=(String)ServicesSearch.serviceFound.elementAt(0);
        System.out.println("Connecting to"+serverURL);
        StreamConnection streamConnection=(StreamConnection) Connector.open(serverURL);
        //send test string
        OutputStream ops=streamConnection.openOutputStream();
        System.out.println("send test string...");
        String hexstr="cc96020301010002";
        byte[] rq=HexStringToBinary(hexstr);
        ops.write(rq);
        ops.flush();

        //response
        System.out.println("response...");
        InputStream ins=streamConnection.openInputStream();
        byte[] data=new byte[21];
        ins.read(data);
        byte[] hp= Arrays.copyOfRange(data,14,15);
        byte[] lp=Arrays.copyOfRange(data,16,17);
        byte[] hr=Arrays.copyOfRange(data,18,19);
        System.out.println(binary(data,16));
        System.out.println("16 进制　"+binary(hp,16)+" "+binary(lp,16)+" "+binary(hr,16));
        System.out.println("10 进制　"+binary(hp,10)+" "+binary(lp,10)+" "+binary(hr,10));
    }

    public static String binary(byte[] bytes, int radix){
        return (new BigInteger(1,bytes)).toString(radix);
    }
    public static byte[] HexStringToBinary(String src){
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
