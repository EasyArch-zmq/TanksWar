package com.zmq.work.workThreads;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 * 处理返回给前置机服务器的数据，有组ID，和相关数据
 */
public class ReturnFrontServerThread implements Runnable{
    private String info;

    public ReturnFrontServerThread(String info) {
       this.info=info;
    }

    @Override
    public void run() {
        InetAddress address = null;//
        int port = 8800;//
        byte[] data2 = null;
        DatagramPacket packet2 = null;
        try {
            /**
             * 处理返回的数据
             */
            data2 = info.getBytes();
            packet2 = new DatagramPacket(data2, data2.length, address, port);
            DatagramSocket socket=new DatagramSocket();
            socket.send(packet2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //socket.close();不能关闭
    }
}
