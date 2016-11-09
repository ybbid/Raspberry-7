package com.cltsp.util;

import java.io.IOException;
import java.net.*;

/**
 * Created by leacher on 16-11-1.
 */
public class LFConnectServ {
    public static void Broadcast(){
        int port=10000;//监听端口和目的端口
        DatagramSocket sendDs;
        DatagramPacket sendDp;
        byte[] sendBuf=new byte[1024];
        try {
            sendDs=new DatagramSocket(3388, InetAddress.getByName("192.168.253.1"));
            sendDp=new DatagramPacket(sendBuf,1024,InetAddress.getByName("255.255.255.255"),port);
            while (true){
                //循环发送UUID和密码
                sendDp.setData(sendBuf,0,1);
                sendDs.send(sendDp);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
