package com.buzz.rpc.framework;

import java.util.HashMap;
import java.util.Map;

public class ConfMonitor {

    public static Map<String, Class> conf = new HashMap<>();

    static {
        conf.put("com.buzz.rpc.framework.IHelloService", IHelloServiceImpl.class);
    }
}
