package com.cltsp.bluetooth;

import javax.bluetooth.*;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by leacher on 16-8-3.
 */
public class BluetoothDeviceDiscovery implements DiscoveryListener{

    //Object used for waiting,private lock.method wait and notify owned Object class.
    private final static Object lock=new Object();

    //Vector containing the diveces discovered.
    public static Vector<RemoteDevice> vecDevices=new Vector<>();
    //main method of the application
    public static void main(String[] args) throws IOException{
        //Create an instance of this class.
        BluetoothDeviceDiscovery bluetoothDeviceDiscovery=new BluetoothDeviceDiscovery();

        //Display local device address and name
        LocalDevice localDevice=LocalDevice.getLocalDevice();
        System.out.println("Address : "+localDevice.getBluetoothAddress());
        System.out.println("Name : "+localDevice.getFriendlyName());

        //Find devices
        DiscoveryAgent agent=localDevice.getDiscoveryAgent();
        System.out.println("Starting device inquiry...");
        //bluetoothDeviceDiscover implements DiscoveryListener
        agent.startInquiry(DiscoveryAgent.GIAC,bluetoothDeviceDiscovery);
        try {
            synchronized (lock){
                lock.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        /*In there,code mast wait released lock then continue. */
        System.out.println("Device inquiry completed.");
        //Print all devices in vecDevices
        int deviceCount=vecDevices.size();

        if (deviceCount<=0){
            System.out.println("No Devices Found.");
        }else {
            System.out.println("Bluetooth Devices:");
            for (int i=0;i<deviceCount;i++){
                RemoteDevice remoteDevice=vecDevices.elementAt(i);
                System.out.println((i+1)+". "+remoteDevice.getBluetoothAddress()+"("+remoteDevice.getFriendlyName(true)+")");
            }
        }
    }

    /*This call back method will be called for each discovered blutooth devices.*/
    @Override
    public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
        System.out.println("Device discovered : "+remoteDevice.getBluetoothAddress());
        //Add the devices to the vector
        if (!vecDevices.contains(remoteDevice)){
            vecDevices.addElement(remoteDevice);
        }
    }

    //No need to implements this method since services are not being discovered.
    @Override
    public void servicesDiscovered(int transID, ServiceRecord[] serviceRecords) {}

    //No need to implements this method since sercices are not being discovered.
    @Override
    public void serviceSearchCompleted(int transID, int respCode) {}


    @Override
    public void inquiryCompleted(int discType) {
        synchronized (lock){
            lock.notify();
        }
        switch (discType){
            case DiscoveryListener.INQUIRY_COMPLETED:
                System.out.println("Inquiry completed.");
                break;
            case DiscoveryListener.INQUIRY_TERMINATED:
                System.out.println("Inquiry terminated.");
                break;
            case DiscoveryListener.INQUIRY_ERROR:
                System.out.println("Inquiry error.");
                break;
            default:
                System.out.println("Unknown Response Code");
                break;
        }
    }
}
