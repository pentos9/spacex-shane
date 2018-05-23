package com.buzz.test.core.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileUtilsTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        try {
            FileUtils.copyFile(new File("/Users/lucas/Downloads/a.bck"), new File("/Users/lucas/Downloads/b.bck"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
