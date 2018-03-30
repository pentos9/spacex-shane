package com.buzz.test;


import com.buzz.common.codec.CodecUtil;
import com.buzz.common.print.PrintUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class CodecTest {

    @Test
    public void testBase64() {
        String data = "Der letzte Mohikaner";
        CodecUtil codecUtil = new CodecUtil();
        String md5hex = codecUtil.md5Hex(data);
        PrintUtil.print(md5hex);
        Assert.assertTrue(StringUtils.isNoneBlank(md5hex));
    }
}
