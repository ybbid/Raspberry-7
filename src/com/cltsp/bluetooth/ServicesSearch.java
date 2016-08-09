package com.cltsp.bluetooth;

import javax.bluetooth.*;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by leacher on 16-8-3.
 */
public class ServicesSearch implements DiscoveryListener{
    //This is common services UUID,can search more services UUID in internet.
    static final UUID SerialPort=new UUID(0x1101);
    static final UUID OBEX_OBJECT_PUSH=new UUID(0x1105);
    static final UUID OBEX_FILE_TRANSFER=new UUID(0x1106);
    private static final Object lock=new Object();

    public static Vector serviceFound=new Vector();

    public static void main(String[] args) throws IOException {
        LocalDevice localDevice=LocalDevice.getLocalDevice();
        DiscoveryAgent agent=localDevice.getDiscoveryAgent();

        BluetoothDeviceDiscovery.main(null);
        serviceFound.clear();

        UUID servicesUUID=SerialPort;
        //if received var form outside,use that creating uuid
        if ((args!=null)&&(args.length>0)){
            servicesUUID=new UUID(args[0],false);
        }

        UUID[] searchUuidSet=new UUID[]{servicesUUID};
        int[] attrIDs=new int[]{
          0x0100 //Service name
        };
        for (RemoteDevice r:BluetoothDeviceDiscovery.vecDevices){
            System.out.println("Search service on "+r.getBluetoothAddress()+"("+r.getFriendlyName(true)+")");
            agent.searchServices(attrIDs,searchUuidSet,r,new ServicesSearch());
        }
        try {
            synchronized (lock){
                lock.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Service search completed.");
        int serviceCount=serviceFound.size();
        if (serviceCount<=0){
            System.out.println("No service Found.");
        }else {
            for (int i=0;i<serviceCount;i++){

            }
        }
    }


    //Device discovered.
    @Override
    public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {}

    @Override
    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
        for (int i=0;i<servRecord.length;i++){
            String url=servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT,false);
            if (url==null){
                continue;
            }
            serviceFound.add(url);
            DataElement serviceName=servRecord[i].getAttributeValue(0x0100);
            if (serviceName!=null){
                System.out.println("service "+serviceName.getValue()+" found "+url);
            }else {
                System.out.println("service found "+url);
            }
        }
    }

    @Override
    public void serviceSearchCompleted(int transID, int respCode) {
        System.out.println("Method:service search completed.");
        synchronized (lock){
            lock.notify();
        }
    }

    //Device inquiry.
    @Override
    public void inquiryCompleted(int i) {}
}
