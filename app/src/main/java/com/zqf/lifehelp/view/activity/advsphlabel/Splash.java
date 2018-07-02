package com.zqf.lifehelp.view.activity.advsphlabel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.app.App;
import com.zqf.lifehelp.utils.Constants;
import com.zqf.lifehelp.view.activity.main.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;

/**
 * Created by zqf on 2017/5/31.
 * 闪屏页面
 */

public class Splash extends Activity {

    @Bind(R.id.splash_bg_iv)
    ImageView splash_bg_iv;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.login_background).into(splash_bg_iv);
        HiPermission.create(Splash.this).style(R.style.PermissionBlueStyle).checkMutiPermission(MyHiPermission);
    }

    /**
     * 权限检测回调结果
     */
    private PermissionCallback MyHiPermission = new PermissionCallback() {
        @Override
        public void onClose() {
            //用户关闭权限操作--退出App
            Logger.i("-PermissionCallback--onClose");
            finish();
        }

        @Override
        public void onFinish() {
            //所有权限申请完成
            Logger.i("-PermissionCallback--onFinish");
            MainIntent();
        }

        @Override
        public void onDeny(String permission, int position) {
            Logger.i("-PermissionCallback--onDeny");
        }

        @Override
        public void onGuarantee(String permission, int position) {
            Logger.i("-PermissionCallback--onGuarantee");
        }
    };

    /**
     * 跳转主页面或Tag页面判断
     */
    private void MainIntent() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (App.getSp().getBoolean(Constants.isSelectTag, false)) {
                    startActivity(new Intent(Splash.this, MainActivity.class));
                } else {
                    startActivity(new Intent(Splash.this, TagSelection.class));
                }
                finish();
            }
        }, 1200);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }
}
