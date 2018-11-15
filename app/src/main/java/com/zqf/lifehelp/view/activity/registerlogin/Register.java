package com.zqf.lifehelp.view.activity.registerlogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.db.daoImpl.UserDao;
import com.zqf.lifehelp.db.daoImpl.UserDaoImpl;
import com.zqf.lifehelp.db.table.User;
import com.zqf.lifehelp.factory.base.NBaseActivity;
import com.zqf.lifehelp.utils.TipUtil;
import com.zqf.lifehelp.utils.appmanager.AppManager;
import com.zqf.lifehelp.utils.tipdialog.TipComDiaLog;

import java.sql.SQLException;
import java.util.UUID;

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
    @Bind(R.id.register_back_img)
    ImageView registerBackImg;
    @Bind(R.id.register_psw_open_close_tv)
    ImageView registerPswOpenCloseTv;
    @Bind(R.id.register_sure_btn)
    Button registerSureBtn;
    private UserDao userDao;
    private String nick_name;
    private String pasw;
    private String phone_num;

    @Override
    protected int getLayout() {
        return R.layout.register_layout;
    }

    @Override
    protected void initView() {
        userDao = new UserDaoImpl();
    }

    @OnClick({R.id.register_back_img, R.id.register_psw_open_close_tv, R.id.register_sure_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_back_img:
                finish();
                break;
            case R.id.register_psw_open_close_tv:

                break;
            case R.id.register_sure_btn:
                //注册失败会抛出HyphenateException
                try {
                    Logger.e(userDao.getAllUser().toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                nick_name = registerNicknameEdit.getText().toString().trim();
                pasw = registerPswEdit.getText().toString().trim();
                phone_num = registerPhonenumEdit.getText().toString().trim();
                mregisterUser(nick_name, pasw);
                break;
        }
    }

    //    {
//        "action": "post",
//            "application": "1fa973d0-86cf-11e6-9089-c7738f07272e",
//            "path": "/users",
//            "uri": "https://a1.easemob.com/yiteng123/familyapp/users",
//            "entities": [
//        {
//            "uuid": "4163c800-85a0-11e8-a9a8-2980d8365c7a",
//                "type": "user",
//                "created": 1531378397312,
//                "modified": 1531378397312,
//                "username": "test1",
//                "activated": true
//        }
//        ],
//        "timestamp": 1531378397314,
//            "duration": 0,
//            "organization": "yiteng123",
//            "applicationName": "familyapp"
//    }
    //注册用户
    private void mregisterUser(final String nick_name, final String pasw) {
        if (TipUtil.toastTip(nick_name, "请输入昵称!")) {
            if (TipUtil.toastTip(pasw, "请输入密码!")) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //同步方法
                            EMClient.getInstance().createAccount(nick_name, pasw);
                            //存储当前用户
                            String uuid = UUID.randomUUID().toString().replace("-", "_");
                            User user = new User();
                            user.setUser_id(uuid);
                            user.setAccount(nick_name);
                            user.setPassword(pasw);
                            user.setPhonennum(phone_num);
                            userDao.insert(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TipComDiaLog.TipSureError(Register.this, "注册成功！请重新登录",
                                            false, false, true, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            }, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                    AppManager.getAppManager().finishAllActivity();
                                                    Intent intent = new Intent(Register.this, Login.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                                    startActivity(intent);
                                                }
                                            });
                                    finish();
                                }
                            });
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            ToastUtils.showShort("注册失败!");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }
}
