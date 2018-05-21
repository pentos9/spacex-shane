package com.buzz.test.core.nio;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioSocketServer {
    private static Logger logger = LoggerFactory.getLogger(NioSocketServer.class);

    private String ip;
    private int port;

    private Selector selector;


    public NioSocketServer(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() throws IOException {
        logger.info("Server is starting:");
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(ip, port));
        serverSocketChannel.configureBlocking(false);
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        logger.info("Server starts successfully! ip:{},port", ip, port);

        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel sc = ssc.accept();
                    logger.info("client server address: ip:{},port{}", sc.getRemoteAddress(), sc.socket().getPort());
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    receive(selectionKey);
                }

            }
        }
    }

    public void receive(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(5 * 1024);
        byteBuffer.clear();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        readBuffer.clear();
        while (true) {
            try {
                int byteRead = socketChannel.read(readBuffer);
                if (byteRead > 0) {
                    readBuffer.flip();
                    byteBuffer.put(readBuffer);
                    readBuffer.clear();
                } else {
                    break;
                }

                byteBuffer.flip();
                String request = new String(byteBuffer.array());
                logger.info("receive data:{}", request.trim());
                String response = "hello world,this is message from server!";
                ByteBuffer writeBuffer = ByteBuffer.wrap(response.getBytes());
                socketChannel.write(writeBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
