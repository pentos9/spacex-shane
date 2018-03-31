package com.buzz.common.codec;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class CodecUtil implements ICodecUtil {

    private static final Random random = new Random();

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
        byte[] salt = new byte[12];
        random.nextBytes(salt);
        return salt;
    }

    private String getCharSet() {
        StringBuilder sb = new StringBuilder();

        for (int ch = '0'; ch <= '9'; ch++) {
            sb.append((char) ch);
        }

        for (int ch = 'A'; ch <= 'Z'; ch++) {
            sb.append((char) ch);
        }

        for (int ch = 'a'; ch <= 'z'; ch++) {
            sb.append((char) ch);
        }

        return sb.toString();
    }

    private String shuffle(String plainText) {
        List<Character> characters = new ArrayList<>();

        for (char ch : plainText.toCharArray()) {
            characters.add(ch);
        }

        StringBuilder sb = new StringBuilder();

        while (characters.size() != 0) {
            Character ch = characters.remove(random.nextInt(characters.size()));
            sb.append(ch);
        }

        return sb.toString();
    }

    public String generateRandomPassword(int length) {
        StringBuilder passwordBuilder = new StringBuilder();

        String charset = getCharSet();
        char[] chars = charset.toCharArray();

        for (int ch = 0; ch < length; ch++) {
            int charIndex = random.nextInt(charset.length());
            passwordBuilder.append(chars[charIndex]);
        }

        String password = shuffle(passwordBuilder.toString());

        return password;
    }
}
