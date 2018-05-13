package com.buzz.common.id;

import java.util.UUID;

public class UUIdUtils {
    public static String uuid() {
        final String uuidText = UUID.randomUUID().toString();
        return uuidText.replace("-", "");
    }
}
