package com.cltsp.bluetooth;

import com.cltsp.bean.Turgoscope;
import com.cltsp.impl.DevDiscoveryListenerImpl;
import com.cltsp.util.LFDateServ;
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
    private String macaddr;
    private long threadID=this.currentThread().getId();
    public final static HashMap<String,String> ConnetUrlmap=new HashMap<>();
    public ConnectService(RemoteDevice remoteDevice){
        this.lastDevice=remoteDevice;
        this.macaddr=lastDevice.getBluetoothAddress();
    }

    @Override
    public void run() {
        if (ConnetUrlmap.containsKey(macaddr)){
            try {
                ToConnet();
            } catch (IOException e) {
                System.out.println("ConnectService "+threadID+" : "+e.getMessage());
                offlineDevice();
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
                System.out.println("ConnectService "+threadID+" : "+e.toString());
                offlineDevice();
            }
        }
        System.out.println("ConnectService "+threadID+" : Thread end.");
    }

    public void ToConnet() throws IOException {
        String servURL=ConnetUrlmap.get(macaddr);
        if(servURL!=null){
            //流要关闭
            System.out.println("ConnectService "+threadID+" : "+"Connect to "+servURL+"...");
            StreamConnection streamConnection=(StreamConnection) Connector.open(servURL);
            OutputStream ops=streamConnection.openOutputStream();
            System.out.println("ConnectService "+threadID+" : "+"Send to "+servURL+"...");
            String hexstr="cc96020301010002";
            byte[] rq= NumConver.HexStringToBinary(hexstr);
            ops.write(rq);
            ops.flush();
            ops.close();
            //response
            System.out.println("ConnectService "+threadID+" : "+"Wait "+servURL+" response...");
            InputStream ins=streamConnection.openInputStream();
            byte[] data=new byte[21];
            ins.read(data);
            //封装数据上传
            Turgoscope turgoscope=new Turgoscope();
            turgoscope.setDatetime(LFDateServ.getNowTime());
            turgoscope.setMacAddress(macaddr);
            turgoscope.setMaxBp(binary(Arrays.copyOfRange(data,14,15),10));
            turgoscope.setMinBp(binary(Arrays.copyOfRange(data,16,17),10));
            turgoscope.setHr(binary(Arrays.copyOfRange(data,18,19),10));
            System.out.println("Test: "+turgoscope.getDatetime()+"\n"
                    +turgoscope.getMaxBp()+"\n"
                    +turgoscope.getMinBp()+"\n"
                    +turgoscope.getHr());
            System.out.println("ConnectService "+threadID+" : "+"Connect "+servURL+" completed.");
            ins.close();
            streamConnection.close();
            offlineDevice();
        }else {
            System.out.println("ConnectService "+threadID+" : Not found service in "+macaddr);
            offlineDevice();
        }
    }

    public void offlineDevice(){
        // TODO: 16-8-17  将连接完成的设备和不能连接的设备从在线设备list中删除
        synchronized (DevDiscoveryListenerImpl.lock){
            BluetoothDeviceDiscovery.remDevices.remove(lastDevice);
        }
        System.out.println("ConnectService "+threadID+" : "+"removed "+macaddr+" on online device list.");
    }
}
