package com.buzz.common.codec;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class CodecUtil implements ICodecUtil {
    @Override
    public String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }

    @Override
    public String sha1Hex(String data) {
        return DigestUtils.sha1Hex(data.getBytes());
    }

    @Override
    public String sha256Hex(String data) {
        return DigestUtils.sha256Hex(data.getBytes());
    }

    @Override
    public String base64Encode(String data) {
        return Base64.encodeBase64String(data.getBytes());
    }

    @Override
    public String base64Decode(String data) {
        Base64 base64 = new Base64();
        return new String(base64.decode(data));

    }

    @Override
    public byte[] getSalt() {
        return new byte[0];
    }
}
