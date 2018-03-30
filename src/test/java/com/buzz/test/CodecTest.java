package com.buzz.test;


import com.buzz.common.codec.CodecUtil;
import com.buzz.common.print.PrintUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class CodecTest {

    private CodecUtil codecUtil = new CodecUtil();

    @Test
    public void testBase64() {
        String data = "Der letzte Mohikaner";
        String md5hex = codecUtil.md5Hex(data);
        PrintUtil.print(md5hex);
        Assert.assertTrue(StringUtils.isNoneBlank(md5hex));
    }

    @Test
    public void testGenerateRandomPassword() {
        int length = 20;
        String password = codecUtil.generateRandomPassword(length);
        Assert.assertTrue(password.length() == length);
    }
}
