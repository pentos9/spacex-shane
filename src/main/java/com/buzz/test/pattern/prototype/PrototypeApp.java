package com.buzz.test.pattern.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrototypeApp {

    private static Logger logger = LoggerFactory.getLogger(PrototypeApp.class);

    public static void main(String[] args) throws CloneNotSupportedException {
        Phone phone = new Phone("iphonex", Color.BLACK);
        Phone clonePhone = phone.clone();
        logger.info(String.format("phone:%s , copyPhone:%s", phone, clonePhone));

        MacBook macbook = (MacBook) PrototypeFactory.getInstance(AppleDevice.MACBOOK);
        logger.info(String.format(macbook.toString()));
        logger.info(String.format("clone macbook:%s", macbook.clone()));

    }
}
