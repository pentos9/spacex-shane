package com.buzz.test.core.nio.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class ChatRoomClient {

    private Logger logger = LoggerFactory.getLogger(ChatRoomClient.class);

    private Selector selector;
    private static final int port = 9999;

    private Charset charset = Charset.forName("utf-8");
    private SocketChannel socketChannel = null;
    private String name = "";
    private static String USER_EXIST = "system message: user exist,please change a name:";
    private static String USER_CONTENT_SPLIT = "#@#";

    public void init() throws IOException {
        this.selector = Selector.open();
        this.socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", port));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new ClientThread()).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if ("".equals(line)) {
                continue;
            }
            if ("".equals(name)) {
                name = line;
                line = name + USER_CONTENT_SPLIT;
            } else {
                line = name + USER_CONTENT_SPLIT + line;
            }

            socketChannel.write(charset.encode(line));
        }
    }

    private class ClientThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    int readyChannels = selector.select();
                    if (readyChannels == 0) {
                        continue;
                    }
                    Set<SelectionKey> keySet = new HashSet<>(selector.keys());
                    Iterator<SelectionKey> iterator = keySet.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        dealWithSelectionKey(key);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void dealWithSelectionKey(SelectionKey key) throws IOException {
            if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                String content = "";
                while (socketChannel.read(byteBuffer) > 0) {
                    byteBuffer.flip();
                    content += charset.decode(byteBuffer);
                }

                if (USER_EXIST.equals(content)) {
                    name = "";
                }

                logger.info(content);
                key.interestOps(SelectionKey.OP_READ);

            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatRoomClient().init();
    }
}
