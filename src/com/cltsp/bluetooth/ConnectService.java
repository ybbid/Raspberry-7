package com.cltsp.bluetooth;

import static com.cltsp.bluetooth.BluetoothDeviceDiscovery.remDevices;

/**
 * Created by leacher on 16-8-3.
 */
public class ConnectService implements Runnable {
    public final static Object conlock=new Object();

    @Override
    public void run() {
        synchronized (conlock){
            try {
                conlock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //取出刚刚发现的设备去建立连接
            RFCOMMThread rt=new RFCOMMThread(remDevices.getLast());
            rt.start();
        }

    }

    public static void main(String[] args) {
        BluetoothDeviceDiscovery.startSearch();
        ConnectService connectService=new ConnectService();
        connectService.run();
    }
}
