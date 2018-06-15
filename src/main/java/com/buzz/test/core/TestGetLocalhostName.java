package com.buzz.test.core;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class TestGetLocalhostName {
    public static void main(String[] args) {
        System.out.println(new Date());
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(new Date());


    }
}
