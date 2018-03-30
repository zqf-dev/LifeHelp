package com.zqf.lifehelp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.app.App;
import com.zqf.lifehelp.view.adapter.TagAdapter;
import com.zqf.lifehelp.view.base.NewBaseActivity;
import com.zqf.lifehelp.view.customview.FlowTagLayout;
import com.zqf.lifehelp.view.customview.OnTagSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zqf on 2017/6/1.
 * 个人喜好标签选择
 */
public class TagSelection extends NewBaseActivity {

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tagselect_layout);
        ButterKnife.bind(this);
        setSteepStatusBar(true);
        init();
    }

    private void init() {
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
        List<String> dataSource = new ArrayList<>();
        dataSource.add("天气预报");
        dataSource.add("菜谱大全");
        dataSource.add("周公解梦");
        dataSource.add("八字算命");
        dataSource.add("健康知识");
        dataSource.add("今日油价");
        dataSource.add("基站查询");
        mAdapter.onlyAddAll(dataSource);
    }

    @OnClick({R.id.regist_tv, R.id.login_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regist_tv:
                //注册

                break;
            case R.id.login_tv:
                //登录

                break;
        }
    }

    @OnClick(R.id.tag_next_btn)
    public void onViewClicked() {
        App.getSp().put("isSelectTag", true);//是否选择了标签
        SaveTag();
        startActivity(new Intent(TagSelection.this, MainActivity.class));
        finish();
    }

    /**
     * 保存选中的标签
     */
    private void SaveTag() {
        if (TextUtils.isEmpty(mSb.toString())
                || mSb.toString().equals("null")
                || mSb.toString().length() == 0) {
            String tag_msg = "天气预报,菜谱大全,周公解梦,八字算命,健康知识,今日油价,基站查询";
            App.getSp().put("tag", tag_msg);
        } else {
            App.getSp().put("tag", mSb.toString());
        }
    }
}
