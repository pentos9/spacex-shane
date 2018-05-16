package com.buzz.common.exception;

public class ExceptionUtil {

    public static boolean isBizException(Throwable t) {
        return t instanceof BizException;
    }
}
