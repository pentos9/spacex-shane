package com.buzz.test.core.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketClientTest {
    public void startClient() throws IOException, InterruptedException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8090);
        SocketChannel client = SocketChannel.open(inetSocketAddress);
        System.out.println("Client starts...");
        String threadName = Thread.currentThread().getName();
        String[] messages = new String[]{threadName + ": test1", threadName + ": test2", threadName + ": test3"};
        for (int i = 0; i < i; i++) {
            byte[] message = new String(messages[i]).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            client.write(buffer);
            System.out.println(messages[i]);
            buffer.clear();
            Thread.sleep(1000L);
        }
        client.close();

    }

    public static void main(String[] args) {
        Runnable clientBootstrap = () -> {
            try {
                new SocketClientTest().startClient();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(clientBootstrap, "client-" + i).start();
        }
    }
}
