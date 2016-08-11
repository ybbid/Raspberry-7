package com.cltsp.bluetooth;

import javax.bluetooth.*;

import static com.cltsp.bluetooth.BluetoothDeviceDiscovery.localDevice;
import static com.cltsp.util.BluetoothUUID.Serial_Port_Profile;
public class ServicesSearchThread extends Thread{
    /*这里只实现了搜索SPP协议的方法*/
    private RemoteDevice remoteDevice;
    private String devAddr;
    private final Object lock=new Object();
    private long threadId=this.currentThread().getId();
    private ServicesSearchThread(){

    }
    public ServicesSearchThread(RemoteDevice remoteDevice){
        this.remoteDevice=remoteDevice;
        this.devAddr=this.remoteDevice.getBluetoothAddress();
    }

    @Override
    public void run() {
        /*searchUuidSet 定义了要搜索的协议的uuid集合*/
        UUID[] searchUuidSet=new UUID[]{Serial_Port_Profile};
        /*attrIDs 定义了属性集合，0x0100 是server */
        int[] attrIDs=new int[]{0x0100};
        DiscoveryAgent agent= localDevice.getDiscoveryAgent();
        System.out.println("ServicesSearchThread "+threadId+" : "+"search service in "+remoteDevice.getBluetoothAddress()+"...");
        try {
            agent.searchServices(attrIDs, searchUuidSet, this.remoteDevice, new DiscoveryListener() {
                @Override
                public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {}

                @Override
                public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
                    for (int i=0;i<servRecord.length;i++){
                        String url=servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT,false);
                        if (url==null){
                            continue;
                        }
                        ConnectService.ConnetUrlmap.put(devAddr,url);
                        DataElement serviceName=servRecord[i].getAttributeValue(0x0100);
                        if (serviceName!=null){
                            System.out.println("ServicesSearchThread "+threadId+" : "+"service "+serviceName.getValue()+" found "+url);
                        }else {
                            System.out.println("ServicesSearchThread "+threadId+" :service found "+url);
                        }
                    }
                }
                @Override
                public void serviceSearchCompleted(int i, int i1) {
                    synchronized (lock){
                        lock.notifyAll();
                        System.out.println("ServicesSearchThread "+threadId+" : notifyAll service search completed.");
                    }
                }
                @Override
                public void inquiryCompleted(int i) {}
            });
        } catch (BluetoothStateException e) {
            e.printStackTrace();
        }

        synchronized (lock){
            try {
                System.out.println("ServicesSearchThread "+threadId+" : wait service search completed...");
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("ServicesSearchThread "+threadId+" : search service in "+remoteDevice.getBluetoothAddress()+" end.");

    }

}
