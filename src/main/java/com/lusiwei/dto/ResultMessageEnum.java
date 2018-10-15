package com.lusiwei.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * Created  by lusiwei on 2018/10/12
 * @author lusiwei
 */
public enum ResultMessageEnum {
    //用户名错误
    USERNAME_ERROR("001","用户名错误"),
    //密码错误
    PASSWORD_ERROR("002","密码错误"),
    //账号未激活
    STATU_FAIL("003","账号未激活"),
    //用户名正确
    USERNAME_TRUE("004","用户名正确"),
    PASSWORD_TRUE("005","密码正确"),
    STATU_TRUE("006","账号激活成功"),
    EMAIL_AVAILABLE("007","邮箱可用"),
    EMAIL_UNAVAILABLE("008","邮箱不可用"),
    USERNAME_AVAILABLE("009","用户名可用"),
    USERNAME_UNAVAILABLE("010","用户名不可用"),

    CHECKCODE_TRUE("011","验证码正确"),
    CHECKCODE_FALSE("012","验证码错误"),

    FAVORITE_SUCCESS("favorite_success","收藏成功"),
    FAVORITE_FAIL("favorite_fail","收藏失败");


    private String code;
    private String message;

    ResultMessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":\"")
                .append(code).append('\"');
        sb.append(",\"message\":\"")
                .append(message).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
