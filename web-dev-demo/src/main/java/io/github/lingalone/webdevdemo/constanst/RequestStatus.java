package io.github.lingalone.webdevdemo.constanst;

public enum RequestStatus {

    SUCCESS(200, "成功"),
    PARAMSVALID(400,"参数非法")
    ;


    private Integer code;
    private String msg;

    RequestStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
