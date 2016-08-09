package com.cltsp.bluetooth;

 /*
 * *************************************
 * Created by leacher on 16-8-3.
 * *************************************
 * spp(Serial Port Profile) 协议连接线程
 * 拿到一个远程设备实例去尝试spp协议连接
 * *************************************
 */

import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.InputStream;

public class RFCOMMThread extends Thread{

    private RemoteDevice remoteDevice;

    public RFCOMMThread(RemoteDevice remoteDevice){
        this.remoteDevice=remoteDevice;
    }
    @Override
    public void run() {
        StreamConnection conn;
        try {
            conn = (StreamConnection) Connector.open("btspp://"+remoteDevice.getBluetoothAddress());
        } catch (IOException e) {
            conn=null;
        }
        String str="000";
        if (conn!=null){
            try {
                InputStream is=conn.openInputStream();
                out
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
