package com.buzz.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {
    private String errmsg;
    private String errcode;

    public ErrorMessage() {
    }

    public ErrorMessage(String errmsg, String errcode) {
        this.errmsg = errmsg;
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }
}
