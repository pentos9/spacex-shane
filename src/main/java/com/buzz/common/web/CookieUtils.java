package com.buzz.common.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CookieUtils {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public CookieUtils(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public Cookie getCookieObject(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals(name))
                    return cookie;
        return null;
    }

    public String getCookie(String name, String defaultValue) {
        Cookie cookie = getCookieObject(name);
        return cookie != null ? cookie.getValue() : defaultValue;
    }

    public String getCookie(String name) {
        return getCookie(name, null);
    }

    public Integer getCookieToInt(String name) {
        return getCookieToInt(name, null);
    }

    public Integer getCookieToInt(String name, Integer defaultValue) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : defaultValue;
    }

    public Long getCookieToLong(String name, Long defaultValue) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(name) : defaultValue;
    }

    public Long getCookieToLong(String name) {
        return getCookieToLong(name, null);
    }

    public Cookie[] getCookieObjects() {
        Cookie[] cookies = request.getCookies();
        return cookies != null ? cookies : new Cookie[0];
    }

    public void setCookie(String name, String value, int maxAgeInSeconds, boolean isHttpOnly) {
        doSetCookie(name, value, maxAgeInSeconds, null, null, isHttpOnly);
    }

    public void setCookie(String name, String value, int maxAgeInSeconds) {
        doSetCookie(name, value, maxAgeInSeconds, null, null, null);
    }

    public void setCookie(HttpServletResponse response, Cookie cookie) {
        response.addCookie(cookie);
    }

    public void setCookie(String name, String value, int maxAgeInSeconds, String path, boolean isHttpOnly) {
        doSetCookie(name, value, maxAgeInSeconds, path, null, isHttpOnly);
    }

    public void setCookie(String name, String value, int maxAgeInSeconds, String path) {
        doSetCookie(name, value, maxAgeInSeconds, path, null, null);
    }

    public void removeCookie(String name) {
        doSetCookie(name, null, 0, null, null, null);
    }


    public void removeCookie(String name, String path) {
        doSetCookie(name, null, 0, path, null, null);
    }

    public void removeCookie(String name, String path, String domain) {
        doSetCookie(name, null, 0, path, domain, null);
    }

    private void doSetCookie(String name, String value, int maxAgeInSeconds, String path, String domain, Boolean isHttpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAgeInSeconds);
        if (path == null) {
            path = "/";
        }
        cookie.setPath(path);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        if (isHttpOnly != null) {
            cookie.setHttpOnly(isHttpOnly);
        }

        response.addCookie(cookie);
    }

}
