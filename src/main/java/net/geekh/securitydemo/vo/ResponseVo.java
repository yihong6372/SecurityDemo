package net.geekh.securitydemo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/12 23:27
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVo<T> {
    private Integer code;

    private String msg;

    private T data;

    public ResponseVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseVo(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
    public ResponseVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
