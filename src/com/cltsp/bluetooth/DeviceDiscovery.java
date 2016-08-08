package com.cltsp.bluetooth;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by leacher on 16-8-8.
 */
public class DeviceDiscovery implements Runnable{

    public static final ConcurrentLinkedQueue<RemoteDevice> Devices_quene=new ConcurrentLinkedQueue<>();

    @Override
    public void run() {


    }

    DiscoveryListener listener=new DiscoveryListener() {
        @Override
        public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {

        }

        @Override
        public void servicesDiscovered(int i, ServiceRecord[] serviceRecords) {}

        @Override
        public void serviceSearchCompleted(int i, int i1) {}

        @Override
        public void inquiryCompleted(int i) {

        }
    };

}
