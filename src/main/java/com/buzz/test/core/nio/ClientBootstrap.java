package com.buzz.test.core.nio;

public class ClientBootstrap {
    public static void main(String[] args) {
        NioSocketClient client = new NioSocketClient();
        client.start();
    }
}
