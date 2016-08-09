package com.cltsp.bluetooth;

import javax.bluetooth.UUID;

/**
 * Created by leacher on 16-8-3.
 */
public class ConnectService {
    //This is common services UUID,can search more services UUID in internet.
    static final UUID SerialPort=new UUID(0x1101);
    static final UUID OBEX_OBJECT_PUSH=new UUID(0x1105);
    static final UUID OBEX_FILE_TRANSFER=new UUID(0x1106);
}
