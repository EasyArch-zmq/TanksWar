package com.zmq.work;

import com.zmq.work.net.TcpServer;
import com.zmq.work.net.UdpServer;

/**
 * 此服务器有两个服务：
 * 1.面向战斗计算服务器的UDP数据传输
 * 2.面向客户端的TCP长连接
 */
public class Server {
    public static void main(String[] args) {
    new UdpServer(7777);
    new TcpServer(8888);
    }
}
