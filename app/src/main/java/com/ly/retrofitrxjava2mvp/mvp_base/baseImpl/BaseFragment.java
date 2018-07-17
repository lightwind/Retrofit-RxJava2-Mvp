package com.ly.retrofitrxjava2mvp.mvp_base.baseImpl;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ly.retrofitrxjava2mvp.mvp_base.BasePresenter;
import com.ly.retrofitrxjava2mvp.mvp_base.BaseView;

/**
 * Create by LiuYang on 2018/7/17 16:38
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    protected P mPresenter;
    public Context mContext;

    // view是否创建
    private boolean isViewCreate = false;
    // view是否可见
    private boolean isViewVisible = false;
    // 是否是第一次加载
    private boolean isFirst = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreate = true;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isViewVisible = isVisibleToUser;
        if (isVisibleToUser && isViewCreate) {
            visibleToUser();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isViewVisible) {
            visibleToUser();
        }
    }

    /**
     * 懒加载
     * 让用户可见
     */
    protected void visibleToUser() {
        if (isFirst) {
            firstLoad();
            isFirst = false;
        }
    }

    /**
     * 懒加载
     * 让用户可见
     * 第一次加载
     */
    protected void firstLoad() {

    }

    protected abstract P initPresenter();

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        isViewCreate = false;
        super.onDestroyView();
    }
}
