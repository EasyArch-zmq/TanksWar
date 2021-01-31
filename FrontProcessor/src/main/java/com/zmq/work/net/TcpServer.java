package com.zmq.work.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 监听和客户端连接的端口，使用netty多线程处理，自定义协议 TCP
 * https://blog.csdn.net/feng_xiaoshi/article/details/88431353
 */
public class TcpServer{
    private int port;
    private ServerSocketChannel serverSocketChannel;

    public TcpServer(int port){
                this.port = port;
                 bind();
    }
    private void bind() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("TcpServerThread启动了！");
                /**
                 * 收到请求后，把数据发送到战斗计算服务器
                 */
                //服务端要建立两个group，一个负责接收客户端的连接，一个负责处理数据传输
                //连接处理group
                EventLoopGroup boss = new NioEventLoopGroup();
                //事件处理group
                EventLoopGroup worker = new NioEventLoopGroup();
                ServerBootstrap bootstrap = new ServerBootstrap();
                // 绑定处理group
                try {
                    bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                            //保持连接数
                            .option(ChannelOption.SO_BACKLOG, 1024)
                            //有数据立即发送
//                            .option(ChannelOption.TCP_NODELAY, true)
                            //保持连接
                            // 第2次握手服务端向客户端发送请求确认，同时把此连接放入队列A中，
                            // 然后客户端接受到服务端返回的请求后，再次向服务端发送请求，表示准备完毕，此时服务端收到请求，把这个连接从队列A移动到队列B中，
                            // 此时A+B的总数，不能超过SO_BACKLOG的数值，满了之后无法建立新的TCP连接,2次握手后和3次握手后的总数
                            // 当服务端从队列B中按照FIFO的原则获取到连接并且建立连接[ServerSocket.accept()]后，B中对应的连接会被移除，这样A+B的数值就会变小
                            //此参数对于程序的连接数没影响，会影响正在准备建立连接的握手。
                            .childOption(ChannelOption.SO_KEEPALIVE, true)
                            .childHandler(new TcpChannelInitializer());

                    ChannelFuture future = bootstrap.bind(port).sync();
                    future.channel().closeFuture().sync();
                }catch (InterruptedException e) {
//            LOGGER.error("netty服务器异常：{}", e.getMessage());
                }
                finally {
                    //优雅地退出，释放线程池资源
                    boss.shutdownGracefully();
                    worker.shutdownGracefully();
                }
            }
        });
        thread.start();
    }

}
