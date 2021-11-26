package org.acme;

import java.util.List;

public class ResponseC {
    String status;
    List<User> data;
    String msg;
    String error;

    public ResponseC(){}

    public ResponseC(String status, List<User> data, String msg, String error) {
        this.status = status;
        this.data = data;
        this.msg = msg;
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
