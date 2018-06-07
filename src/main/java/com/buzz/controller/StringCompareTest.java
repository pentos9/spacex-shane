package com.buzz.controller;

import java.io.*;

public class StringCompareTest {
    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("python localhost");
            InputStream is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
