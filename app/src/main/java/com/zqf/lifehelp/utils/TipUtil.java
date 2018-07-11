package com.zqf.lifehelp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.zqf.lifehelp.utils.widget.LoadingDialog;

/**
 * class from
 * Created by zqf
 * Time 2018/5/17 9:38
 */

public class TipUtil {

    //判断getText的值是否
    public static boolean toastTip(String params, String tip_msg) {
        if (TextUtils.isEmpty(params) || params.equals("null")) {
            ToastUtils.showShort(tip_msg);
        } else {
            return true;
        }
        return false;
    }


    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public static void BaseStartActivityForResult(Activity context, Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivityForResult(intent, requestCode);
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
