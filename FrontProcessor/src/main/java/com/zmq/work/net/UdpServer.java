package com.zmq.work.net;

import io.netty.channel.socket.ServerSocketChannel;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 监听和战斗计算服务器通信的端口转发给客户端  UDP
 * 收到数据后
 */
public class UdpServer{
    private int port;
    public UdpServer(int port){
        this.port = port;
        bind();
    }
    private void bind() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("UdpServerThread启动了！");
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket(8800);
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                DatagramPacket packet = null;
                byte[] data = null;
                int count = 0;
                System.out.println("***服务器端启动，等待发送数据***");
                while (true) {
                    data = new byte[1024];//创建字节数组，指定接收的数据包的大小
                    packet = new DatagramPacket(data, data.length);
                    try {
                        socket.receive(packet);//此方法在接收到数据报之前会一直阻塞
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // Thread thread = new Thread(new UDPThread(socket, packet));
                    //   thread.start();
                    count++;
                    System.out.println("服务器端被连接过的次数：" + count);
                    InetAddress address = packet.getAddress();
                    System.out.println("当前客户端的IP为：" + address.getHostAddress());
                }
            }
        });
        thread.start();
    }

}
