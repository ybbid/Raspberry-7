package com.cltsp.servlet;

import com.cltsp.bluetooth.BluetoothDeviceDiscovery;

import javax.servlet.http.HttpServlet;


/**
 * Created by leacher on 16-8-16.
 */
public class ServerMain extends HttpServlet {
    //后台程序的入口Servlet类
    static {
        BluetoothDeviceDiscovery.startSearch();
        System.out.println("启动时加载");
    }
}
