package com.lusiwei.dto;

/**
 * Created  by lusiwei on 2018/10/11
 * @author lusiwei
 */
public class ResultMessage {
    private boolean flag;
    private String message;

    public ResultMessage() {
    }

    public ResultMessage(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
