package com.ly.retrofitrxjava2mvp.mvp_base.baseImpl;

import lombok.Getter;
import lombok.Setter;

/**
 * 请求结果基础bean；仅用于判断操作是否成功
 * Create by LiuYang on 2018/7/17 16:27
 */
@Getter
@Setter
public class BaseBean<T> {
    private String code;
    private String message;
    private T data;
}
