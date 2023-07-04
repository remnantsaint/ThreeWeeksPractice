package org.jshand.support.common.core.domain;

import io.swagger.annotations.ApiModelProperty;


public final class Ret<T> {
    @ApiModelProperty(value = "状态码", required = true)
    private Integer code;

    @ApiModelProperty(value = "提示信息")
    private String message;

    @ApiModelProperty(value = "数据")
    private T data;

    private Ret(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Ret nohandler() {
        return new Ret(ResultCode.NOHANDLER.getCode(), ResultCode.NOHANDLER.getMessage(), null);
    }

    public static Ret success(Object obj) {
        return new Ret(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), obj);
    }

    public static Ret failed() {
        return new Ret(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), null);
    }

    public static Ret failed(String message) {
        return new Ret(ResultCode.FAILED.getCode(), message, null);
    }

    public static Ret notoken() { //没有登录
        return new Ret(ResultCode.NOTOKEN.getCode(), ResultCode.NOTOKEN.getMessage(), null);
    }

    public static Ret nopremiss() { //没有权限
        return new Ret(ResultCode.NOPERMISS.getCode(), ResultCode.NOPERMISS.getMessage(), null);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}