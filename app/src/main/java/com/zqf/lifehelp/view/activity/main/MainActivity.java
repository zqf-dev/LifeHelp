package com.zqf.lifehelp.view.activity.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.view.fragment.ContentFg;
import com.zqf.lifehelp.view.fragment.FgLeft;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主界面入口---采用主界面fragment--ContentFg
 * ---->侧边采用LeftFg
 * 以一个Activity管理多个fragment操作形式
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.fl_content)
    FrameLayout flContent;
    @Bind(R.id.fl_left_menu)
    FrameLayout flLeftMenu;
    @Bind(R.id.main_drawer)
    DrawerLayout mainDrawer;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initfg();
    }

    private void initfg() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fl_content, new ContentFg(), "0");
        ft.replace(R.id.fl_left_menu, new FgLeft(), "1");
        ft.commitAllowingStateLoss();
    }

    /**
     * @param keyCode-->键码
     * @param event-->Key动作
     * @return 是否
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mainDrawer.isDrawerOpen(findViewById(R.id.fl_left_menu))) {
                mainDrawer.closeDrawers();
                return false;
            } else {
                if (System.currentTimeMillis() - exitTime > 2000) {
                    Toast.makeText(this, "再点一次退出", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
