package com.zmq.work.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class TcpChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new TcpSocketHandler());
    }
}
