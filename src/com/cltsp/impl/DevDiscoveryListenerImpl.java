package com.cltsp.impl;

import com.cltsp.bluetooth.ConnectService;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import static com.cltsp.bluetooth.BluetoothDeviceDiscovery.dislock;
import static com.cltsp.bluetooth.BluetoothDeviceDiscovery.remDevices;

public class DevDiscoveryListenerImpl implements DiscoveryListener {
    public final static Object lock=new Object();
    @Override
    public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
        //System.out.println("Device " + btDevice.getBluetoothAddress() + " found");
        synchronized (lock) {

            //tracelog 竟态条件会引起问题
            if (!remDevices.contains(remoteDevice)) {
                remDevices.add(remoteDevice);
                /*synchronized (conlock){
                  conlock.notifyAll();
                }
                PS:这里conlock释放锁的时间间隔小于try conlock.wait()的时间间隔。
                当几乎同时conlock.notifyAll()的时候，由于conlock.wait()那段代码执行周期比较长，故只能收到第一个conlock.notifyALl()的请求
                这里加一个让conlock等候的时候通知的代码
                或者加一个线程休眠的代码
                或者new Thread 处理*/
                (new ConnectService(remoteDevice)).start();
            }
        }
    }

    @Override
    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {}

    @Override
    public void serviceSearchCompleted(int i, int i1) {}

    @Override
    public void inquiryCompleted(int i) {
        synchronized (dislock){
            dislock.notifyAll();
        }
    }
}