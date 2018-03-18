package com.buzz.test;

import com.buzz.util.ClassUtil;

import javax.annotation.Resource;
import java.net.URL;
import java.util.Set;

public class ClassLoaderHelper {

    public static void main(String[] args) {
        ClassLoader classLoader = ClassUtil.getClassLoader();
        System.out.println(classLoader.getResource("").getPath());
        Set<Class<?>> classSet = ClassUtil.getClassSet("com.buzz");

        //Set<Class<?>> classSet = ClassUtil.getClassSet("com.buzz");
        for (Class clazz : classSet) {
            System.out.println(clazz.getName());
        }

    }
}
