package com.zmq.work.entry;

import java.net.InetAddress;

public class FrontIpPort {
    private InetAddress address;
    private int port;

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
