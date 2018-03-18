package com.buzz.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class MonitorTestUtils {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        System.out.println("freeMemory:" + freeMemory);
        System.out.println("maxMemory:" + maxMemory);
        System.out.println("totalMemory:" + totalMemory);

        OperatingSystemMXBean opsm = ManagementFactory.getOperatingSystemMXBean();
        Double SystemLoadAverage = opsm.getSystemLoadAverage();
        String arch = opsm.getArch();
        String name = opsm.getName();
        String version = opsm.getVersion();

        System.out.println("SystemLoadAverage:" + SystemLoadAverage);
        System.out.println("arch:" + arch);
        System.out.println("name:" + name);
        System.out.println("version:" + version);

        try {
            Process process = runtime.exec("ps aux");
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
