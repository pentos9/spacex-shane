package com.buzz.test;

import com.buzz.common.codec.CodecUtil;


import org.apache.commons.lang3.StringUtils;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CodecTestApp {

    private Logger logger = LoggerFactory.getLogger(CodecTestApp.class);

    public static void main(String[] args) {
        CodecTestApp codecTestApp = new CodecTestApp();
        codecTestApp.run();

    }

    public void run() {
        String data = "life on Mars";

        CodecUtil codecUtil = new CodecUtil();
        String md5Hex = codecUtil.md5Hex(data);
        String sha1Hex = codecUtil.sha1Hex(data);
        String sha256Hex = codecUtil.sha256Hex(data);
        String base64Encode = codecUtil.base64Encode(data);

        String base64Decode = codecUtil.base64Decode(base64Encode);

        String randomPassword = codecUtil.generateRandomPassword(18);

        logger.info(String.format("md5Hex:[%s]", md5Hex));
        logger.info(String.format("sha1Hex:[%s]", sha1Hex));
        logger.info(String.format("sha256Hex:[%s]", sha256Hex));
        logger.info(String.format("md5Hex:[%s]", md5Hex));
        logger.info(String.format("base64Encode:[%s]", base64Encode));
        logger.info(String.format("base64Decode:[%s]", base64Decode));

        logger.info(String.format("randomPassword:[%s]", randomPassword));

    }

    public void print(char[] charArray) {

        List<Character> list = new ArrayList<>();
        for (char ch : charArray) {
            list.add(ch);
        }

        for (int index = 0; index < list.size(); index++) {
            if (index % 10 == 0 && index != 0) {
                System.out.println();
            }

            System.out.print(list.get(index) + "\t");
        }

    }

    @Test
    public void codec() {
        String data = "life on Mars";
        CodecUtil codecUtil = new CodecUtil();
        String base64Encode = codecUtil.base64Encode(data);
        Assert.assertTrue(StringUtils.isNotBlank(base64Encode));

    }
}
