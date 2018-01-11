package com.zqf.lifehelp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.model.entity.LocalCityModel;

import java.util.List;

/**
 * class from
 * Created by zqf
 * Time 2018/1/11 12:59
 */

public class CityAdapter extends BaseExpandableListAdapter {
    private List<LocalCityModel> mProvince;
    private Context mContext;

    public CityAdapter(Context context, List<LocalCityModel> bean) {
        this.mProvince = bean;
        this.mContext = context;
    }

    //  获得某个父项的某个子项
    @Override
    public Object getChild(int parentPos, int childPos) {
        return mProvince.get(parentPos).getCityBeanList().get(childPos);
    }

    //  获得父项的数量
    @Override
    public int getGroupCount() {
        return mProvince.size();
    }

    //  获得某个父项的子项数目
    @Override
    public int getChildrenCount(int parentPos) {
        return mProvince.get(parentPos).getCityBeanList().size();
    }

    //  获得某个父项
    @Override
    public Object getGroup(int parentPos) {
        return mProvince.get(parentPos).getTitle();
    }

    //  获得某个父项的id
    @Override
    public long getGroupId(int parentPos) {
        return parentPos;
    }

    //  获得某个父项的某个子项的id
    @Override
    public long getChildId(int parentPos, int childPos) {
        return childPos;
    }

    //  按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //  获得父项显示的view
    @Override
    public View getGroupView(int parentPos, boolean b, View convertView, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expand_group, viewGroup, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.label_expand_group);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.tvTitle.setText(mProvince.get(parentPos).getTitle());
        return convertView;
    }

    //  获得子项显示的view
    @Override
    public View getChildView(int parentPos, int childPos, boolean b, View convertView, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expand_child, viewGroup, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.label_expand_child);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.tvTitle.setText(mProvince.get(parentPos).getCityBeanList().get(childPos).getDistrict());
        return convertView;
    }

    //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    static class GroupViewHolder {
        TextView tvTitle;
    }

    static class ChildViewHolder {
        TextView tvTitle;
    }
}
