package com.zqf.lifehelp.view.activity.advsphlabel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.app.App;
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
    ImageView splashBgIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        ButterKnife.bind(this);
        /**
         渐变动画
         */
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1f);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (App.getSp().getBoolean("splash_permiss", false)) {
                    MainIntent();
                } else {
                    //检测权限
                    HiPermission.create(Splash.this)
                            .animStyle(R.style.PermissionAnimFade)
                            .checkMutiPermission(MyHiPermission);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splashBgIv.startAnimation(animation);
    }

    /**
     * 权限检测回调结果
     */
    private PermissionCallback MyHiPermission = new PermissionCallback() {
        @Override
        public void onClose() {
            //用户关闭权限操作--退出App
            Logger.i("-PermissionCallback--onClose");
            App.getSp().put("splash_permiss", false);
            finish();
        }

        @Override
        public void onFinish() {
            //所有权限申请完成
            Logger.i("-PermissionCallback--onFinish");
            App.getSp().put("splash_permiss", true);
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
        if (App.getSp().getBoolean("isSelectTag", false)) {
            startActivity(new Intent(Splash.this, MainActivity.class));
        } else {
            startActivity(new Intent(Splash.this, TagSelection.class));
        }
        finish();
    }
}
