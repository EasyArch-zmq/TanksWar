package com.zmq.work;



import com.zmq.work.entry.FrontIpPort;
import com.zmq.work.threadPoolUtil.ThreadPoolUtil;
import com.zmq.work.workThreads.FrontServerRequestHandleTread;
import com.zmq.work.workThreads.GoServerHandleThread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8800);
        DatagramPacket packet = null;
        byte[] data = null;
        int count = 0;
        System.out.println("***服务器端启动，等待发送数据***");
        while(true){
            data = new byte[1024];//创建字节数组，指定接收的数据包的大小
            packet = new DatagramPacket(data, data.length);
            socket.receive(packet);//此方法在接收到数据报之前会一直阻塞
            InetAddress address = packet.getAddress();
            String IP=address.getHostAddress();//拿到I
            /**
             * 判断是哪边服务器的请求
             */
            if ("前置机服务器IP".equals(IP)){
                //来自前置机服务器的请求
                if (count==0){
                    FrontIpPort frontIpPort=new FrontIpPort();
                    frontIpPort.setAddress(address);
                    frontIpPort.setPort(packet.getPort());
                    /**
                     * 存到Redis里，备用
                     */
                    count++;
                }
                ThreadPoolUtil.getExecutor().execute(new FrontServerRequestHandleTread(packet));
            }else {
                //来自go服务器的请求
                ThreadPoolUtil.getExecutor().execute(new GoServerHandleThread(socket,packet));
            }

        }

    }

}
