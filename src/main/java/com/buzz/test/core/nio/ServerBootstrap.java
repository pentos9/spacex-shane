package com.buzz.test.core.nio;

import java.io.IOException;

public class ServerBootstrap {
    public static void main(String[] args) {
        NioSocketServer server = new NioSocketServer("127.0.0.1", 8888);
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
