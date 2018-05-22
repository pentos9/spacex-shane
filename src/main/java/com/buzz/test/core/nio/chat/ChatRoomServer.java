package com.buzz.test.core.nio.chat;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ChatRoomServer {
    private Logger logger = LoggerFactory.getLogger(ChatRoomServer.class);

    private Selector selector;
    private final int port = 9999;
    private Charset charset = Charset.forName("utf-8");
    private static HashSet<String> users = new HashSet<>();
    private static String USER_EXIST = "System message: user exist,please select another name!";
    private static String USER_CONTENT_SPLIT = "#@#";
    private static boolean flag = false;


    public void init() throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        logger.info("Server is listening now...");
        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }

            Set<SelectionKey> selectKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                dealWithSelectedKey(serverSocketChannel, key);
            }

        }

    }

    public void dealWithSelectedKey(ServerSocketChannel serverSocketChannel, SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            key.interestOps(SelectionKey.OP_ACCEPT);
            logger.info("Sever is listening from client:" + socketChannel.getRemoteAddress());
            socketChannel.write(charset.encode("please input your name:"));
        }

        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            StringBuilder content = new StringBuilder();
            while (socketChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                content.append(charset.decode(byteBuffer));
                logger.info("Server is listening from client:" + socketChannel.getRemoteAddress() + ",data received is " + content);
                key.interestOps(SelectionKey.OP_READ);
            }

            if (content.length() > 0) {
                String[] arrayContent = content.toString().split(USER_CONTENT_SPLIT);
                if (arrayContent != null && arrayContent.length == 1) {
                    String name = arrayContent[0];
                    if (users.contains(name)) {
                        socketChannel.write(charset.encode(USER_EXIST));
                    } else {
                        users.add(name);
                        int num = onlineNum(selector);
                        String message = "welcome " + name + " to chat room! and online number is " + num;
                        broadcast(selector, null, message);

                    }
                } else if (arrayContent != null && arrayContent.length > 1) {
                    String name = arrayContent[0];
                    String message = content.substring(name.length() + USER_CONTENT_SPLIT.length());
                    message = name + " say " + message;
                    if (users.contains(name)) {
                        broadcast(selector, socketChannel, message);
                    }

                }
            }
        }

    }

    public static int onlineNum(Selector selector) {
        int count = 0;
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            if (channel instanceof SocketChannel) {
                count++;
            }
        }
        return count;
    }

    public void broadcast(Selector selector, SocketChannel except, String content) throws IOException {
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != except) {
                SocketChannel dest = (SocketChannel) targetChannel;
                dest.write(charset.encode(content));

            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatRoomServer().init();
    }

}
