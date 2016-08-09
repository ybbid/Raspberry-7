package com.cltsp.bluetooth;

import com.cltsp.impl.DiscoveryListenerImpl;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import java.util.LinkedList;

/**
 * Created by leacher on 16-8-3.
 */
public class BluetoothDeviceDiscovery implements Runnable{
    /*Linkedlist 不是线程安全的,用来存储在线的蓝牙设备*/
    public final static LinkedList<RemoteDevice> remDevices=new LinkedList<>();
    public final static LocalDevice localDevice=getLocalDevice();

    //对象锁
    public final static Object lock=new Object();
    private static boolean isSearched=false;
    private BluetoothDeviceDiscovery(){

    }

    public static void startSearch(){
        //确保只有一个线程
        if (!isSearched){
            Thread Search=new Thread(new BluetoothDeviceDiscovery());
            Search.start();
            isSearched=true;
        }
    }


    @Override
    public void run() {
        remDevices.clear();
        DiscoveryAgent agent=localDevice.getDiscoveryAgent();
        DiscoveryListenerImpl listener=new DiscoveryListenerImpl();
        while (true){
            synchronized (lock){
                try {
                    agent.startInquiry(DiscoveryAgent.GIAC,listener);
                    lock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static LocalDevice getLocalDevice(){
        try {
            return LocalDevice.getLocalDevice();
        } catch (BluetoothStateException e) {
            return null;
        }
    }

}
