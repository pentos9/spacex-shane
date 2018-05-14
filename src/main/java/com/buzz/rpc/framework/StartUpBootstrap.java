package com.buzz.rpc.framework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartUpBootstrap {

    private static final int port = 8080;

    public static void main(String[] args) {
        exportRpc();
    }

    public static void exportRpc() {
        try {
            ServerSocket serverSocket = new ServerSocket(9001);

            while (true) {
                Socket socket = serverSocket.accept();
                new RpcThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
