package com.buzz.common.codec;


public interface ICodecUtil {

    String md5Hex(String data);

    String sha1Hex(String data);

    String sha256Hex(String data);

    String base64Encode(String data);

    String base64Decode(String data);

    byte[] getSalt();

}
