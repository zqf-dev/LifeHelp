package com.zqf.lifehelp.view.activity.registerlogin;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.factory.base.NBaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

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
    @Bind(R.id.register_back_img)
    ImageView registerBackImg;
    @Bind(R.id.register_psw_open_close_tv)
    TextView registerPswOpenCloseTv;
    @Bind(R.id.register_again_psw_open_close_tv)
    TextView registerAgainPswOpenCloseTv;
    @Bind(R.id.register_sure_btn)
    Button registerSureBtn;

    @Override
    protected int getLayout() {
        return R.layout.register_layout;
    }

    @Override
    protected void initView() {}

    @OnClick({R.id.register_back_img, R.id.register_psw_open_close_tv
            , R.id.register_again_psw_open_close_tv, R.id.register_sure_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_back_img:
                finish();
                break;
            case R.id.register_psw_open_close_tv:
                break;
            case R.id.register_again_psw_open_close_tv:
                break;
            case R.id.register_sure_btn:
                break;
        }
    }
}
