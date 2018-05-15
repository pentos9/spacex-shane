package com.buzz.rpc.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartUpBootstrap {

    private static Logger logger = LoggerFactory.getLogger(StartUpBootstrap.class);

    private static final int port = 9001;

    public static void main(String[] args) {
        exportRpc(port);
    }

    public static void exportRpc(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();
                new RpcThread(socket).start();
                logger.info("StartUpBootstrap starts now.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
