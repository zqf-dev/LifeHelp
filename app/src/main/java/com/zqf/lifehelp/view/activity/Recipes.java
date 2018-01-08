package com.zqf.lifehelp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.model.entity.MenuSort;
import com.zqf.lifehelp.service.manage.DataManager;
import com.zqf.lifehelp.utils.Constants;
import com.zqf.lifehelp.view.adapter.RecipesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * class from 菜谱大全
 * Created by zqf
 * Time 2017/6/27 9:57
 */

public class Recipes extends Activity {


    @Bind(R.id.recipes_recycle)
    RecyclerView recipesRecycle;
    private RecipesAdapter mAdapter;
    private DataManager mDataManager;
    private String[] imageUrl = {
            "http://f2.mob.com/null/2015/08/18/1439876711867.jpg",
            "http://f2.mob.com/null/2015/08/18/1439876719915.jpg",
            "http://f2.mob.com/null/2015/08/18/1439877012674.jpg"};
    private List<MenuSort> mList = new ArrayList<>();
    private List<MenuSort.MenuName> mXList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_layout);
        ButterKnife.bind(this);
        mDataManager = new DataManager(this);
        menu_data();
    }

    /**
     * 请求菜谱大全类别以及精选数据
     */
    private void menu_data() {
        mDataManager.getMenuData(Constants.MOBKEY).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.e("LifeHelp", "++++" + response.body());
                try {
                    JSONObject json = new JSONObject((String) response.body());
                    if (json.optString("retCode").equals("200")) {
                        JSONArray array = json.optJSONArray("childs");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj1 = array.optJSONObject(i);
                            MenuSort bean1 = new MenuSort();
                            bean1.setName(obj1.optJSONObject("categoryInfo").optString("name"));
                            bean1.setCtgId(obj1.optJSONObject("categoryInfo").optString("ctgId"));
                            bean1.setParentId(obj1.optJSONObject("categoryInfo").optString("parentId"));
                            mList.add(bean1);
                            for (int j = 0; j < obj1.optJSONArray("childs").length(); j++) {
                                MenuSort.MenuName bean2 = new MenuSort.MenuName();
                                JSONObject obj2 = obj1.optJSONArray("childs").optJSONObject(j)
                                        .optJSONObject("categoryInfo");
                                bean2.setName(obj2.optString("ctgId"));
                                bean2.setName(obj2.optString("name"));
                                bean2.setName(obj2.optString("parentId"));
                                mXList.add(bean2);
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(Recipes.this, json.optString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }
}
