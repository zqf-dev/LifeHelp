package com.zqf.lifehelp.view.activity.registerlogin;

import android.widget.EditText;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.factory.base.NBaseActivity;

import butterknife.Bind;

/**
 * class from 注册页面
 * Created by zqf
 * Time 2018/3/19 10:06
 */

public class Register extends NBaseActivity {


    @Bind(R.id.register_nickname_edit)
    EditText registerNicknameEdit;
    @Bind(R.id.register_phonenum_edit)
    EditText registerPhonenumEdit;
    @Bind(R.id.register_psw_edit)
    EditText registerPswEdit;
    @Bind(R.id.register_surepsw_edit)
    EditText registerSurepswEdit;

    @Override
    protected int getLayout() {
        return R.layout.register_layout;
    }

    @Override
    protected void initView() {

    }
}
