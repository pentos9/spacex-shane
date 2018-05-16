package com.buzz.common.exception;

public abstract class AbstractException extends RuntimeException {
    protected String errorMsg = null;

    public AbstractException() {
    }

    public AbstractException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }
}
