package com.cltsp.bean;

import javax.bluetooth.RemoteDevice;

/**
 * Created by leacher on 16-8-8.
 */
public class Device {
    private String deviceName;
    private String deviceMac;

    public Device(RemoteDevice remoteDevice) {
        this.deviceMac=remoteDevice.getBluetoothAddress();
    }
}
