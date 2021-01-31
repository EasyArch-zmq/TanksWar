package com.zmq.work.workThreads;

import com.zmq.work.threadPoolUtil.ThreadPoolUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 处理go服务器过来的数据，拿到组ID，用户账号，动作指令
 * 计算得到结果，返回给go服务器
 *
 */
public class GoServerHandleThread implements Runnable{
    DatagramSocket socket = null;
    DatagramPacket packet = null;


    public GoServerHandleThread(DatagramSocket socket,DatagramPacket packet) {
        this.socket = socket;
        this.packet = packet;
    }

    @Override
    public void run() {
         long ID=0;
        String info = null;
        InetAddress address = null;
        int port = 8800;
        byte[] data2 = null;
        DatagramPacket packet2 = null;
        try {
            info = new String(packet.getData(), 0, packet.getLength());
            System.out.println("我是服务器，客户端说："+info);
            /**
             * 这里new一个线程出来，处理数据帧返回给前置机服务器
             */

            ThreadPoolUtil.getExecutor().execute(new ReturnFrontServerThread(info));

            /**
             * 调用方法，计算有没有角色进入视野，移动后的结果
             */
            address = packet.getAddress();
            port = packet.getPort();
            data2 = "我在响应你！".getBytes();
            packet2 = new DatagramPacket(data2, data2.length, address, port);
            socket.send(packet2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //socket.close();不能关闭
    }
}
