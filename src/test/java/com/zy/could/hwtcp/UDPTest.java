package com.zy.could.hwtcp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date 2021/8/12 上午 10:15
 */
public class UDPTest {

    public static void main(String[] args) {
        try {
            // 1，创建udp服务。通过DatagramSocket对象。
            DatagramSocket socket = new DatagramSocket(8888);
            // 2，确定数据，并封装成数据包。DatagramPacket(byte[] buf, int length, InetAddress
            // address, int port)
            byte[] buf = "你好，世界1".getBytes();
            DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"),
                    4443);
            // 3，通过socket服务，将已有的数据包发送出去。通过send方法。
            socket.send(dp);
            // 4，关闭资源。
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
