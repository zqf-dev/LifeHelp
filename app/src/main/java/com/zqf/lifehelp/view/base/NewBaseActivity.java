package com.zqf.lifehelp.view.base;

<<<<<<< HEAD
import android.app.Activity;
=======
>>>>>>> 8528f9cefeeb088150d594df61cf60cb3df7795a
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.support.v7.app.AppCompatActivity;
>>>>>>> 8528f9cefeeb088150d594df61cf60cb3df7795a
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
<<<<<<< HEAD

=======
>>>>>>> 8528f9cefeeb088150d594df61cf60cb3df7795a
import com.zqf.lifehelp.app.App;
import com.zqf.lifehelp.utils.appmanager.AppManager;

/**
 * Created by zqf on 2017/3/13.
 * 基类Activity
 */
<<<<<<< HEAD
public abstract class NewBaseActivity extends Activity {
=======
public abstract class NewBaseActivity extends AppCompatActivity {
>>>>>>> 8528f9cefeeb088150d594df61cf60cb3df7795a

    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = false;
    /**
     * 是否允许全屏
     **/
    private boolean mAllowFullScreen = true;
    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isAllowScreenRoate = false;
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

<<<<<<< HEAD
=======

>>>>>>> 8528f9cefeeb088150d594df61cf60cb3df7795a
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Activity管理类
        AppManager.getAppManager().addActivity(this);
<<<<<<< HEAD
        //注册RxBus
//        RxBus.get().register(this);
        Log.d(TAG, "BaseActivity-->onCreate()");
        if (isSetStatusBar) {
            steepStatusBar();
        }
=======
        Log.d(TAG, "BaseActivity-->onCreate()");
>>>>>>> 8528f9cefeeb088150d594df61cf60cb3df7795a
    }

    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(NewBaseActivity.this, clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        AppManager.getAppManager().finishActivity(this);
<<<<<<< HEAD
        //解除RxBus注册
//        RxBus.get().unregister(this);
=======
>>>>>>> 8528f9cefeeb088150d594df61cf60cb3df7795a
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * [是否允许全屏]
     *
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * [是否设置沉浸状态栏]
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * [是否允许屏幕旋转]
     *
     * @param isAllowScreenRoate
     */
    public void setScreenRoate(boolean isAllowScreenRoate) {
        this.isAllowScreenRoate = isAllowScreenRoate;
    }

//    public void setShowLoading(String tip_msg) {
//        if (TextUtils.isEmpty(tip_msg)) {
//            tip_msg = "加载中...";
//        }
//        mNetLoadingDialog = NetLoadingDialog.showLoading(this, tip_msg);
//    }
//
//    public void onCancelLoadingDialog() {
//        if (mNetLoadingDialog != null) {
//            mNetLoadingDialog.dismiss();
//            mNetLoadingDialog = null;
//        }
//    }

    //获取Token
    public String getToken() {
        String toke = App.getSp().getString("token");
        if (!TextUtils.isEmpty(toke)) {
            return toke;
        }
        return "";
    }

    //获取userid
    public String getUserid() {
        String userid = App.getSp().getString("user_id");
        if (!TextUtils.isEmpty(userid)) {
            return userid;
        }
        return "";
    }


//    //弹出更多信息的PopupWindows
//    private ApplyVehicDetailPopAdapter popAdapter;
//    private PopupWindow ovPw;
//
//    public void morePqShowPop(final List<ScheduledActivityModelData> modelDatas) {
//        ovPw = new PopupWindow(this);
//        View v = LayoutInflater.from(this).inflate(R.layout.apply_detail_show_pop, null);
//        ListView listview_detail_pop = (ListView) v.findViewById(R.id.listview_detail_pop);
//        popAdapter = new ApplyVehicDetailPopAdapter(modelDatas);
//        listview_detail_pop.setAdapter(popAdapter);//modelDatas
//        popAdapter.setlistener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (int i = 0; i < modelDatas.size(); i++) {
//                    ScheduledActivityModelData modelData = modelDatas.get(i);
//                    CallPhone(modelData.getMobile());
//                }
//            }
//        });
//        ovPw.setBackgroundDrawable(new ColorDrawable(0x000000));
//        ovPw.setContentView(v);
//        ovPw.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
//        ovPw.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
//        ovPw.setAnimationStyle(R.style.detailWindowAnim);
//        ovPw.setFocusable(true);
//        ovPw.setTouchable(true);
//        ovPw.setOutsideTouchable(true);
//        Util.bgalpha(this, 0.7f);
//        ovPw.showAtLocation(v, Gravity.CENTER, 0, 0);
//        ovPw.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                Util.bgalpha(NewBaseActivity.this, 1f);
//                PopDismiss();
//            }
//        });
//    }

    public void CallPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

//    private void PopDismiss() {
//        if (ovPw != null) {
//            ovPw.dismiss();
//            ovPw = null;
//        }
//    }

}
