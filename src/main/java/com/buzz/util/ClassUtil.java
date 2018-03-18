package com.buzz.util;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {

    /**
     * 获取类加载器
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader;
    }

    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> clz = null;

        try {
            clz = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return clz;
    }

    /**
     * 获取指定包下的所有类
     *
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocal = url.getProtocol();
                    if (protocal.equals("file")) {//get from file
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        //
                        Set<String> classNames = getClassFromDir(url.getPath(), packageName, true);
                        //do add class by name
                        doAddClasses(classSet, classNames);

                    } else if (protocal.equals("jar")) {
                        //get from jar
                        JarFile jarFile = null;
                        try {
                            jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (jarFile != null) {
                            Set<String> classNames = getClassNameFromJar(jarFile.entries(), packageName, false);
                            doAddClasses(classSet, classNames);
                        }


                    } else {
                        /*从所有的jar包中查找包名*/
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return classSet;
    }

    public static SortedSet<String> getClassFromDir(String filePath, String packageName, boolean isRecursive) {
        SortedSet<String> classNames = new TreeSet<>();

        File file = new File(filePath);
        File[] files = file.listFiles();
        if (files == null || files.length <= 0) {
            return classNames;
        }

        for (File childFile : files) {
            if (childFile.isDirectory()) {
                Set<String> tempClassNames = getClassFromDir(childFile.getPath(), packageName + "." + childFile.getName(), isRecursive);
                if (tempClassNames != null && !tempClassNames.isEmpty()) {
                    classNames.addAll(tempClassNames);
                }
            } else {
                String fileName = childFile.getName();
                if (fileName.endsWith(".class") && !fileName.contains("$")) {
                    classNames.add(packageName + "." + fileName.replace(".class", ""));
                }
            }

        }
        return classNames;

    }

    public static Set<String> getClassNameFromJar(Enumeration<JarEntry> jarEntries, String packageName, boolean isRecursive) {
        Set<String> classNames = new HashSet<>();

        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String jarEntryName = jarEntry.getName();
            if (jarEntryName.endsWith(".class") && !jarEntryName.contains("$")) {
                jarEntryName = jarEntryName.replace(".class", "");
                classNames.add(jarEntryName);
            }
        }

        return classNames;
    }

    public static void doAddClass(Set<Class<?>> classSet, String className) {
        Class clz = loadClass(className, false);
        classSet.add(clz);
    }

    public static void doAddClasses(Set<Class<?>> classSet, Set<String> classNames) {

        if (classSet == null) {
            return;
        }

        if (classNames == null || classNames.isEmpty()) {
            return;
        }

        for (String name : classNames) {
            Class clazz = loadClass(name, false);
            classSet.add(clazz);
        }

    }
}
