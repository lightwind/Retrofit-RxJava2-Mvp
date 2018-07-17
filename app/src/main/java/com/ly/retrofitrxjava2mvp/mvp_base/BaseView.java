package com.ly.retrofitrxjava2mvp.mvp_base;

/**
 * Create by LiuYang on 2018/7/17 16:23
 */
public interface BaseView {
    void showLoadingDialog(String msg);

    void dismissLoadingDialog();
}
