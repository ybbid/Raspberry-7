package com.cltsp.bluetooth;

import javax.bluetooth.RemoteDevice;
import java.util.HashMap;

import static com.cltsp.bluetooth.BluetoothDeviceDiscovery.remDevices;

/**
 * Created by leacher on 16-8-3.
 */
public class ConnectService implements Runnable {
    public final static Object conlock=new Object();
    private RemoteDevice lastDevice;
    public final static HashMap<String,String> ConnetUrlmap=new HashMap<>();
    @Override
    public void run() {
       while (true){
            System.out.println("等待设备搜索....");
            synchronized (conlock){
                try {
                    conlock.wait();//拿到了锁,后来的设备拿不到连接锁。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lastDevice= remDevices.getLast();
            }
            (new ServicesSearchThread(lastDevice)).start();
        }
    }

    public static void main(String[] args) {
        BluetoothDeviceDiscovery.startSearch();
        ConnectService connectService=new ConnectService();
        connectService.run();
    }
}
