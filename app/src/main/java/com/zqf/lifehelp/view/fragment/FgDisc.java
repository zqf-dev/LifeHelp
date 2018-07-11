package com.zqf.lifehelp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.view.activity.registerlogin.Register;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zqf on 2017/6/6.
 * 发现
 */
//张三：{"code":200,"userId":"1","token":"rkrn61JeVCH0Oqm1aKlA3KPJl4sd+uBnZECc0O4v3NKo6p4s/CRgpaKS8NaWEMnmF6wkdwU0y5X00mYk1XgZow=="}
//王五：{"code":200,"userId":"2","token":"mcW7kI6K1eJw/G775sR3QlqqEqhjq6kZL2zgBiw8wt4a6x6BaPzBUXkAziqsmpaB8VQRu5uT4p0="}
public class FgDisc extends Fragment {

    @Bind(R.id.disc_no_register_tip_tv)
    TextView disc_no_register_tip_tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_disc = inflater.inflate(R.layout.fg_disc, container, false);
        ButterKnife.bind(this, view_disc);
        return view_disc;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.disc_no_register_tip_tv)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.disc_no_register_tip_tv:
                startActivity(new Intent(getActivity(), Register.class));
                break;
        }
    }
}
