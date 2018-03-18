package com.buzz.util.helper;


import com.buzz.util.ClassUtil;
import com.buzz.util.annotation.Controller;

public final class HelperLoader {
    public static void init() {
        Class<?>[] classList = {ClassHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class};
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}
