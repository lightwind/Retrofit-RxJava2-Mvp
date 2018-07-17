package com.ly.retrofitrxjava2mvp.retrofit;

/**
 * TODO 自定义exception用于访问
 * Create by LiuYang on 2018/7/17 16:09
 */
public class ApiException extends RuntimeException {
    private int code;

    private ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public ApiException(String message) {
        super(new Throwable(message));
    }
}
