package com.ordersdelivery.app.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import com.ordersdelivery.R;
import com.ordersdelivery.app.adapter.MyViewPagerAdapter;
import com.ordersdelivery.app.ui.base.BaseActivity;
import com.ordersdelivery.app.ui.main.fragment.FragmentNewOrders;
import com.ordersdelivery.app.ui.main.fragment.FragmentOthersOrders;
import com.ordersdelivery.app.utils.Constants;
import com.ordersdelivery.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private List<String> titles;
    private List<Fragment> fragmentList;
    private MyViewPagerAdapter adapter;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    private void initView() {
        binding.setLang(getLang());
        Intent intent = getIntent();
        user_id = (String) intent.getSerializableExtra(Constants.user_id);
        binding.setUser(getUserModel());
        titles = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titles.add(getString(R.string.newOrders));
        titles.add(getString(R.string.othersOrders));
        binding.tab.setupWithViewPager(binding.pager);

        fragmentList.add(FragmentNewOrders.newInstance(user_id));
        fragmentList.add(FragmentOthersOrders.newInstance(user_id));

        adapter = new MyViewPagerAdapter(getSupportFragmentManager(), PagerAdapter.POSITION_UNCHANGED, fragmentList, titles);

        binding.pager.setAdapter(adapter);
        binding.pager.setOffscreenPageLimit(fragmentList.size());
        for (int i = 0; i < binding.tab.getTabCount(); i++) {
            Log.e("i", i + "");
            View view = ((ViewGroup) binding.tab.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
        }
    }
}