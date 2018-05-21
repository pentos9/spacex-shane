package com.buzz.test.core.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioSocketClient {
    public void start() {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            boolean flag = socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888));
            String res = "Hello Service,this is message from Client!";

            System.out.println("Send:" + res);
            byte[] bs = res.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.wrap(bs);
            socketChannel.write(writeBuffer);
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            socketChannel.read(readBuffer);
            byte[] data = readBuffer.array();
            String message = new String(data);
            System.out.println("Message:" + message.trim());
            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
