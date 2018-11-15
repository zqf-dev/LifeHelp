package com.zqf.lifehelp.view.activity.advsphlabel;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.app.App;
import com.zqf.lifehelp.factory.base.NBaseActivity;
import com.zqf.lifehelp.utils.Constants;
import com.zqf.lifehelp.utils.Util;
import com.zqf.lifehelp.utils.customview.FlowTagLayout;
import com.zqf.lifehelp.utils.customview.OnTagSelectListener;
import com.zqf.lifehelp.view.activity.main.MainActivity;
import com.zqf.lifehelp.view.activity.registerlogin.Login;
import com.zqf.lifehelp.view.activity.registerlogin.Register;
import com.zqf.lifehelp.view.adapter.TagAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zqf on 2017/6/1.
 * 个人喜好标签选择
 */
public class TagSelection extends NBaseActivity {

    @Bind(R.id.tag_tit_tv)
    TextView tagTitTv;
    @Bind(R.id.regist_tv)
    TextView registTv;
    @Bind(R.id.login_tv)
    TextView loginTv;
    @Bind(R.id.tag_flowlayout)
    FlowTagLayout tagFlowlayout;
    @Bind(R.id.tag_next_btn)
    Button tagNextBtn;
    private TagAdapter<String> mAdapter;
    private StringBuilder mSb = new StringBuilder();

    @Override
    protected int getLayout() {
        return R.layout.tagselect_layout;
    }

    @Override
    public void initView() {
        mAdapter = new TagAdapter<>(this);
        tagFlowlayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        tagFlowlayout.setAdapter(mAdapter);
        tagFlowlayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                mSb = new StringBuilder();
                if (selectedList != null && selectedList.size() > 0) {
                    for (int i : selectedList) {
                        mSb.append(parent.getAdapter().getItem(i).toString());
                        mSb.append(",");
                    }
                    tagNextBtn.setBackgroundResource(R.drawable.tag_btn_bg);
                    tagNextBtn.setTextColor(ContextCompat.getColor(TagSelection.this, R.color.white));
                } else {
                    tagNextBtn.setBackgroundResource(R.drawable.tag_btn_bgnull);
                    tagNextBtn.setTextColor(ContextCompat.getColor(TagSelection.this, R.color.black));
                }
                Log.e("TAG", mSb.toString());
            }
        });
        /**
         * 显示的文字数据
         */
        mAdapter.onlyAddAll(Constants.dataSource);
    }

    @OnClick({R.id.regist_tv, R.id.login_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regist_tv:
                //注册
                Util.NextActivity(this, Register.class);
                break;
            case R.id.login_tv:
                //登录
                Util.NextActivity(this, Login.class);
                break;
        }
    }

    @OnClick(R.id.tag_next_btn)
    public void onViewClicked() {
        SaveTag();
        startActivity(new Intent(TagSelection.this, MainActivity.class));
        finish();
    }

    /**
     * 保存选中的标签
     */
    private void SaveTag() {
        App.getSp().put(Constants.isSelectTag, true);//是否选择了标签
        if (TextUtils.isEmpty(mSb.toString())
                || mSb.toString().equals("null")
                || mSb.toString().length() == 0) {
            String tag_msg = "天气预报,身份查询,今日油价,周公解梦,八字算命,健康知识,基站查询";
            App.getSp().put("tag", tag_msg);
        } else {
            App.getSp().put("tag", mSb.toString());
        }
    }
}
