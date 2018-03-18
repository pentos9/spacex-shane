package com.buzz.util.bean;

import com.buzz.util.CastUtil;

import java.util.Map;

public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public long getLong(String name) {
        return CastUtil.getLong(paramMap.get(name));
    }
}
