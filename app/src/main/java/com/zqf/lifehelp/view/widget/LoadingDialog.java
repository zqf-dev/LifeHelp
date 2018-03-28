package com.zqf.lifehelp.view.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.zqf.lifehelp.R;

/**
 * class from 加载中的对话框
 * Created by zqf
 * Time 2018/1/10 15:27
 */

public class LoadingDialog extends ProgressDialog {
    private Context mContext;
    private String mLoadingTip;
    private TextView mLoadingTv;
    private int mstyle;
    private static LoadingDialog sDialog;

    public LoadingDialog(Context context, String content, int style) {
        super(context);
        this.mContext = context;
        this.mLoadingTip = content;
        this.mstyle = style;
        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    //初始化
    private void initView() {
        setContentView(R.layout.base_loading_dialog);
        mLoadingTv = (TextView) findViewById(R.id.loadingTextView);
    }

    /**
     * 设置加载文字
     */
    public void setContent(String str) {
        mLoadingTv.setText(str);
    }

    public static LoadingDialog show(Context context, String message, OnCancelListener cancelListener) {
        if (TextUtils.isEmpty(message)) {
            message = "加载中......";
        }
        sDialog = new LoadingDialog(context, message + "", R.style.ProgressHUD);
        sDialog.show();
        return sDialog;
    }

    /**
     * 销毁
     */
    public void destorydialog() {
        if (sDialog != null) {
            sDialog.dismiss();
        }
    }
}
