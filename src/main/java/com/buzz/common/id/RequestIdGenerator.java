package com.buzz.common.id;

import java.util.UUID;

public class RequestIdGenerator {
    public static String generateId() {
        final String uuidText = UUID.randomUUID().toString();
        return uuidText.replace("-", "");
    }
}
