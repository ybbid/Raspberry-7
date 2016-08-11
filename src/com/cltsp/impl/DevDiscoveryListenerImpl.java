package com.cltsp.impl;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import static com.cltsp.bluetooth.BluetoothDeviceDiscovery.dislock;
import static com.cltsp.bluetooth.BluetoothDeviceDiscovery.remDevices;
import static com.cltsp.bluetooth.ConnectService.conlock;

public class DevDiscoveryListenerImpl implements DiscoveryListener {
    public final static Object lock=new Object();
    @Override
    public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
        //System.out.println("Device " + btDevice.getBluetoothAddress() + " found");
        synchronized (conlock){
            //tracelog 竟态条件会引起问题
            if (!remDevices.contains(remoteDevice)){
                remDevices.add(remoteDevice);
                conlock.notifyAll();
                //这里尝试唤醒，ConnectService 但是可能Connect获得消息（锁） 之前被 deviceDiscovered获得了锁...
                //存在一个竟态条件
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