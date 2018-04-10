package com.zqf.lifehelp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.utils.customview.recycler.SpacesItemDecoration;
import com.zqf.lifehelp.utils.widget.LoadingDialog;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * class from 工具类
 * Created by zqf
 * Time 2017/6/15 16:01
 */

public class Util {

    public static void NextActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    /**
     * 设置分割线
     * 设置布局管器
     */
    public static void RecycleCommSet(Activity activity, RecyclerView commrecycle, int HorizontalOrVertical) {
        //分割线
        int leftRight = (int) activity.getResources().getDimension(R.dimen.size_5dp);
        int topBottom = (int) activity.getResources().getDimension(R.dimen.size_0_5dp);
        int lineColor = ContextCompat.getColor(activity, R.color.tinge_gray);
        commrecycle.addItemDecoration(new SpacesItemDecoration(leftRight, topBottom, lineColor));
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(HorizontalOrVertical);
        commrecycle.setLayoutManager(layoutManager);
    }

    /**
     * 读取Assets文件
     *
     * @param context
     * @return
     */
    public static String ReadDayDayString(Context context, String file_name) {
        InputStream is = null;
        String msg = null;
        try {
            is = context.getResources().getAssets().open(file_name);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            msg = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Number) {
            Number num = (Number) obj;
            if (num.intValue() == 0) {
                return true;
            } else {
                return false;
            }
        } else if (obj instanceof String) {
            String str = (String) obj;
            if ((str == null) || str.equals("")) {
                return true;
            } else {
                return false;
            }
        } else if (obj instanceof Collection<?>) {
            Collection<?> c = (Collection<?>) obj;
            return c.isEmpty();
        } else if (obj instanceof Map<?, ?>) {
            Map<?, ?> m = (Map<?, ?>) obj;
            return m.isEmpty();
        } else if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            return length == 0 ? true : false;
        } else {
            return false;
        }
    }


    public static String QueryIDNumPermissKey(String msg) {
        return msg.substring(msg.length() - 4, msg.length());
    }


    private static LoadingDialog mLoadingDialog;

    //获取网络加载
    public static void setShowLoading(Context context) {
        mLoadingDialog = LoadingDialog.show(context, "加载中....");
    }

    public static void setOnCancelLinstener() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }
}
