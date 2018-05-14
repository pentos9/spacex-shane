package com.buzz.rpc.framework;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class RpcThread extends Thread {
    private Socket socket;

    public RpcThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcObject rpcObject = (RpcObject) objectInputStream.readObject();

            Object object = getObject(rpcObject.getClazz());
            Object reObject = executeMethod(object, rpcObject.getMethodName(), rpcObject.getArgs());

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(reObject);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Object executeMethod(Object object, String methodName, Object[] args) {
        Object objResult = null;
        Class[] classes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            classes[i] = arg.getClass();
        }

        try {

            Method method = object.getClass().getMethod(methodName, classes);
            objResult = method.invoke(object, args);

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return objResult;
    }

    private Object getObject(Class c) {
        Object object = null;

        try {
            object = ConfMonitor.conf.get(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}
