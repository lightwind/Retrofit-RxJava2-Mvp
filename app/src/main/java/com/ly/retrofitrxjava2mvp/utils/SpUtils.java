package com.ly.retrofitrxjava2mvp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * SharedPreferences存取工具类
 * Create by LiuYang on 2018/7/17 16:48
 */
public class SpUtils {

    //配置文件，文件名
    private static final String SHARE_NAME = "config";

    /**
     * 万能put
     */
    public static void put(Context mContext, String key, Object values) {
        if (values instanceof String) {
            putString(mContext, key, (String) values);
        } else if (values instanceof Integer) {
            putInt(mContext, key, (Integer) values);
        } else if (values instanceof Boolean) {
            putBoolean(mContext, key, (Boolean) values);
        } else {
            //出去上面三个类型，都存储为String
            putString(mContext, key, String.valueOf(values));
        }
    }

    /**
     * 存字符串
     */
    public static void putString(Context mContext, String key, String values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, values).apply();
    }


    /**
     * 取字符串
     */
    public static String getString(Context mContext, String key, String values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, values);
    }


    /**
     * 存布尔值
     */
    public static void putBoolean(Context mContext, String key, boolean values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, values).apply();
    }

    /**
     * 取布尔值
     */
    public static boolean getBoolean(Context mContext, String key, boolean values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, values);
    }

    /**
     * 存int值
     */
    public static void putInt(Context mContext, String key, int values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, values).apply();
    }

    /**
     * 取int值
     */
    public static int getInt(Context mContext, String key, int values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, values);
    }

    /**
     * 删除一条字段
     */
    public static void deleShare(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        //单个清理
        sp.edit().remove(key).apply();
    }

    /**
     * 删除全部数据
     */
    public static void deleShareAll(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        //全部清理
        sp.edit().clear().apply();
    }

    /**
     * 查看SharedPreferences的内容
     */
    public static String lookSharePre(Context context) {
        try {
            @SuppressLint("SdCardPath")
            FileInputStream stream = new FileInputStream(new File("/data/data/" + context.getPackageName() +
                    "/shared_prefs", "config.xml"));
            BufferedReader bff = new BufferedReader(new InputStreamReader(stream));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bff.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "未找到当前配置文件！";
    }
}
