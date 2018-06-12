package com.buzz.contract;

import java.io.Serializable;

public class ResultBean<T> implements Serializable {
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int NO_PERMISSION = 2;

    private String msg = "success";
    private int code = SUCCESS;

    private T data;

    public ResultBean() {
    }

    public ResultBean(T data) {
        this.data = data;
    }

    public ResultBean(Throwable throwable) {
        super();
        this.msg = throwable.toString();
        this.code = FAIL;
    }


}
