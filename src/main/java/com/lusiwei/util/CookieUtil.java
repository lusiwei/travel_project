package com.lusiwei.util;

import javax.servlet.http.Cookie;

/**
 * Created  by lusiwei on 2018/10/16
 */
public class CookieUtil {
    public static Cookie getCookieByName(Cookie[] cookies,String cookieName){
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }
        return null;
    }
}
