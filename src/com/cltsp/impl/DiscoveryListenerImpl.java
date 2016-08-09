package com.cltsp.impl;

/*
 * This is the DiscoveryListener implementation for the bluetooth TCK
 * tests. It utilizes public variables that are set when the
 * listener methods are called.  This enables testing to ensure the
 * methods are reached.  It makes parameters such as the
 * ServiceRecord[] and DeviceClass available for testing. It also
 * allows the test to synchronize with it in the
 * serviceSearchComplete() method.  This avoids the unnecessary
 * waiting in the test.
 */
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import static com.cltsp.bluetooth.BluetoothDeviceDiscovery.lock;
import static com.cltsp.bluetooth.BluetoothDeviceDiscovery.remDevices;

public class DiscoveryListenerImpl implements DiscoveryListener {

    @Override
    public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
        //System.out.println("Device " + btDevice.getBluetoothAddress() + " found");
        synchronized (lock){
            if (!remDevices.contains(remoteDevice)){
                remDevices.add(remoteDevice);
            }
        }
    }

    @Override
    public void servicesDiscovered(int i, ServiceRecord[] serviceRecords) {

    }

    @Override
    public void serviceSearchCompleted(int i, int i1) {

    }

    @Override
    public void inquiryCompleted(int i) {
        System.out.println("Device Inquiry completed,and start new Inquiry!");
        for (RemoteDevice r:remDevices){
            System.out.println(r.getBluetoothAddress());
        }
        synchronized (lock){
            lock.notifyAll();
        }
    }
}