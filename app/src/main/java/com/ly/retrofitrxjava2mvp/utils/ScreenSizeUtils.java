package com.ly.retrofitrxjava2mvp.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

/**
 * 获取屏幕宽高的帮助类
 * Create by LiuYang on 2018/7/17 16:47
 */
public class ScreenSizeUtils {

    private static ScreenSizeUtils instance = null;
    private int mScreenWidth, mScreenHeight;

    public static ScreenSizeUtils getInstance(Context mContext) {

        if (instance == null) {
            synchronized (ScreenSizeUtils.class) {
                if (instance == null)
                    instance = new ScreenSizeUtils(mContext);
            }
        }
        return instance;
    }

    private ScreenSizeUtils(Context mContext) {
        WeakReference<Context> contextWeakReference = new WeakReference<>(mContext);
        Context context = contextWeakReference.get();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);

        mScreenWidth = dm.widthPixels;// 获取屏幕分辨率宽度
        mScreenHeight = dm.heightPixels;
    }

    //获取屏幕宽度
    public int getScreenWidth() {
        return mScreenWidth;
    }

    //获取屏幕高度
    public int getScreenHeight() {
        return mScreenHeight;
    }
}
