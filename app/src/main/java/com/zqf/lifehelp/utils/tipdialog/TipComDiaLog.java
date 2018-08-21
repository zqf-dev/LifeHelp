package com.zqf.lifehelp.utils.tipdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by zqf on 2018/7/10.
 * 提示类封装对话框
 */

public class TipComDiaLog {

    private static AlertDialog.Builder mAlertDialog;

    /**
     * 提示窗口
     *
     * @param context          Context
     * @param tip_msg          提示语
     * @param isdiss           是否点击外部消失
     * @param isshowcancle     是否显示取消
     * @param isshowsure       是否显示确定
     * @param onCancelListener 取消监听
     * @param onSurelListener  确认监听
     */
    public static void TipSureError(Context context, String tip_msg, boolean isdiss
            , boolean isshowcancle, boolean isshowsure
            , DialogInterface.OnClickListener onCancelListener
            , DialogInterface.OnClickListener onSurelListener) {
        mAlertDialog = new AlertDialog.Builder(context);
        mAlertDialog.setCancelable(isdiss);
        mAlertDialog.setTitle("温馨提示");
        mAlertDialog.setMessage(tip_msg);
        if (isshowcancle) {
            mAlertDialog.setNegativeButton("取消", onCancelListener);
        }
        if (isshowsure) {
            mAlertDialog.setPositiveButton("确定", onSurelListener);
        }
        mAlertDialog.show();
        mAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
    }
}
