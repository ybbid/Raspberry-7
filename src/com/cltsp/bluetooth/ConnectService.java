package com.cltsp.bluetooth;

import com.cltsp.util.NumConver;

import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;

import static com.cltsp.util.NumConver.binary;

/**
 * Created by leacher on 16-8-3.
 */
public class ConnectService extends Thread {
    public final Object conlock=new Object();
    private RemoteDevice lastDevice;
    private String macaddr=lastDevice.getBluetoothAddress();
    private long threadID=this.currentThread().getId();
    public final static HashMap<String,String> ConnetUrlmap=new HashMap<>();
    public ConnectService(RemoteDevice remoteDevice){
        this.lastDevice=remoteDevice;
    }
    @Override
    public void run() {
        if (ConnetUrlmap.containsKey(macaddr)){
            try {
                ToConnet();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            (new ServicesSearchThread(lastDevice,conlock)).start();
            synchronized (conlock){
                try {
                    conlock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                ToConnet();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public void ToConnet() throws IOException {
        String servURL=ConnetUrlmap.get(macaddr);
        System.out.println("ConnectService "+threadID+" : "+"Connect to "+servURL+"...");
        StreamConnection streamConnection=(StreamConnection) Connector.open(servURL);
        OutputStream ops=streamConnection.openOutputStream();
        System.out.println("ConnectService "+threadID+" : "+"Send to "+servURL+"...");
        String hexstr="cc96020301010002";
        byte[] rq= NumConver.HexStringToBinary(hexstr);
        ops.write(rq);
        ops.flush();
        //response
        System.out.println("ConnectService "+threadID+" : "+"Wait "+servURL+"response...");
        InputStream ins=streamConnection.openInputStream();
        byte[] data=new byte[21];
        ins.read(data);
        //封装数据上传
        byte[] hp= Arrays.copyOfRange(data,14,15);
        byte[] lp=Arrays.copyOfRange(data,16,17);
        byte[] hr=Arrays.copyOfRange(data,18,19);
        System.out.println(binary(data,16));
        System.out.println("16 进制　"+binary(hp,16)+" "+binary(lp,16)+" "+binary(hr,16));
        System.out.println("10 进制　"+binary(hp,10)+" "+binary(lp,10)+" "+binary(hr,10));
        System.out.println("ConnectService "+threadID+" : "+"Connect "+servURL+"completed.");
    }
}
