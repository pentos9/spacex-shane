package com.buzz.util.bean;


import java.lang.reflect.Method;

/**
 * 封装Action
 */
public class Handler {

    /**
     * 请求类
     */
    private Class<?> controllerClass;

    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
