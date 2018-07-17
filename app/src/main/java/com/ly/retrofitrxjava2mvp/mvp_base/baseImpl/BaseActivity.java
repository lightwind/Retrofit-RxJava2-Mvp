package com.ly.retrofitrxjava2mvp.mvp_base.baseImpl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ly.retrofitrxjava2mvp.mvp_base.BasePresenter;
import com.ly.retrofitrxjava2mvp.mvp_base.BaseView;
import com.ly.retrofitrxjava2mvp.utils.ActivityManager;

/**
 * Create by LiuYang on 2018/7/17 16:32
 */
public abstract class BaseActivity<P extends BasePresenter> extends Activity implements BaseView {

    protected P mPresenter;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // 将当前activity添加进入管理栈
        ActivityManager.getInstance().addActivity(this);
        mPresenter = initPresenter();
    }

    /**
     * 在子类中初始化对应的presenter
     *
     * @return 相应的presenter
     */
    protected abstract P initPresenter();

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    protected void onDestroy() {
        // 将当前activity移除管理栈
        ActivityManager.getInstance().removeActivity(this);
        if (mPresenter != null) {
            // 在presenter中解绑释放view
            mPresenter.detach();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
