package com.ly.retrofitrxjava2mvp.mvp_base.baseImpl;

import com.ly.retrofitrxjava2mvp.mvp_base.BasePresenter;
import com.ly.retrofitrxjava2mvp.mvp_base.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Create by LiuYang on 2018/7/17 16:28
 */
public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {

    protected V mView;

    // 将所有正在处理的Subscription都添加到CompositeSubscription中。统一退出的时候注销观察
    private CompositeDisposable mCompositeDisposable;

    public BasePresenterImpl(V view) {
        this.mView = view;
        start();
    }

    @Override
    public void start() {

    }

    @Override
    public void detach() {
        if (mView != null) {
            mView = null;
            unDisposable();
        }
    }

    /**
     * 将Disposable添加
     */
    @Override
    public void addDisposable(Disposable subscription) {
        // csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    /**
     * 在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
     */
    @Override
    public void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
