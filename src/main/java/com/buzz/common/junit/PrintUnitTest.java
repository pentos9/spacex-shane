package com.buzz.common.junit;


import com.buzz.common.print.PrintUtil;
import org.junit.Assert;
import org.junit.Test;


import java.util.Arrays;
import java.util.HashMap;


public class PrintUnitTest {

    @Test
    public void err() {
        PrintUtil.err("junit5 #PrintUtil.err test");
        Assert.assertTrue(true);
    }

    @Test
    public void verbose() {
        PrintUtil.verbose("junit5 #PrintUtil.verbose test");
        Assert.assertTrue(true);
    }

    @Test
    public void array() {
        String[] devices = new String[]{"iPhone", "iMac", "iPad"};
        PrintUtil.print("junit5 #PrintUtil.array test", devices);
        Assert.assertTrue(true);
    }

    @Test
    public void list() {

        PrintUtil.print("junit5 #PrintUtil.list test", Arrays.asList(1, 4, 56, 6, 90), true);
        PrintUtil.print("junit5 #PrintUtil.list test", Arrays.asList(12, 8, 526, 66, 390), true);
        Assert.assertTrue(true);
    }

    @Test
    public void map() {

        HashMap<Object, Object> map = new HashMap<>();
        map.put("iphone5s", "4399");
        map.put("iphone7", "7288");
        map.put("iphonex", "9899");
        PrintUtil.print("junit5 #PrintUtil.map test", map, true);
        Assert.assertTrue(true);
    }
}
