package com.zqf.lifehelp.view.permis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zqf.lifehelp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zqf on 2017/5/23.
 * 6.0权限申请
 */
public class PermissionAct extends Activity {
    @Bind(R.id.sign_permission)
    Button signPermission;
    @Bind(R.id.more_permission)
    Button morePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.sign_permission, R.id.more_permission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sign_permission:

                break;
            case R.id.more_permission:

                break;
        }
    }
}
