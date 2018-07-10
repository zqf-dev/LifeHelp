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

    public static void TipSureError(Context context, String tip_msg
            , DialogInterface.OnClickListener onCancelListener
            , DialogInterface.OnClickListener onSurelListener) {
        mAlertDialog = new AlertDialog.Builder(context);
        mAlertDialog.setTitle("温馨提示");
        mAlertDialog.setMessage(tip_msg);
        mAlertDialog.setNegativeButton("取消", onCancelListener);
        mAlertDialog.setPositiveButton("确定", onSurelListener);
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
