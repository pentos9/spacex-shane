package com.buzz.rpc.framework;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RpcProxy implements InvocationHandler, Serializable {

    private String ip;
    private int port;
    private Class c;

    public RpcProxy(String ip, int port, Class c) {
        this.ip = ip;
        this.port = port;
        this.c = c;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = null;

        Socket socket = new Socket(ip, port);
        RpcObject rpcObject = new RpcObject(c, method.getName(), args);
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;


        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(rpcObject);
        outputStream.flush();

        inputStream = new ObjectInputStream(socket.getInputStream());
        object = inputStream.readObject();

        outputStream.close();
        inputStream.close();
        
        return object;
    }
}
