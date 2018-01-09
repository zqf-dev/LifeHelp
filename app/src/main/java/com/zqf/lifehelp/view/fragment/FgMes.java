package com.zqf.lifehelp.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.utils.Util;
import com.zqf.lifehelp.view.activity.Setting;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zqf on 2017/6/6.
 * 个人中心
 */

public class FgMes extends Fragment {

    @Bind(R.id.avatar_image)
    ImageView avatarImage;
    @Bind(R.id.photo_album_tv)
    TextView photoAlbumTv;
    @Bind(R.id.mes_line1)
    TextView mesLine1;
    @Bind(R.id.collect_tv)
    TextView collectTv;
    @Bind(R.id.setting_tv)
    TextView settingTv;
    private Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_me = inflater.inflate(R.layout.fg_mes, container, false);
        ButterKnife.bind(this, view_me);
        return view_me;
    }

    @OnClick({R.id.avatar_image, R.id.photo_album_tv, R.id.collect_tv, R.id.setting_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.avatar_image:
                break;
            case R.id.photo_album_tv:
                break;
            case R.id.collect_tv:
                break;
            case R.id.setting_tv:
                Util.NextActivity(mActivity, Setting.class);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
