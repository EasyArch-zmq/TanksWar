package com.zmq.work.workThreads;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 处理前置机服务器过来的数据，拿到组ID，用户账号，不用返回数据
 * 存到Redis里 hash： key：组ID field：用户账号 value：用户账号
 */
public class FrontServerRequestHandleTread implements Runnable{

    DatagramPacket packet = null;


    public FrontServerRequestHandleTread(DatagramPacket packet) {
        this.packet = packet;
    }

    @Override
    public void run() {
        String info = null;//接收到的数据
        InetAddress address = null;//来源处的IP，端口
        int port = 8800;
        byte[] data2 = null;
        DatagramPacket packet2 = null;//需要返回的数据
        info = new String(packet.getData(), 0, packet.getLength());//从零开始取数据
        /**
         * 将数据组ID存入redis
         */
        System.out.println("我是服务器，客户端说："+info);
        //socket.close();不能关闭
    }
}
