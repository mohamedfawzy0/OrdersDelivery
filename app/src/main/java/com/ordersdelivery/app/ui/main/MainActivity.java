package com.ordersdelivery.app.ui.main;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {
    private ActivityMainBinding binding;
    private List<String> titles;
    private List<Fragment> fragmentList;
    private MyViewPagerAdapter adapter;
    private String user_id;
    private DatePickerDialog datePickerDialog;
    private String FromDate = null;
    private String ToDate = null;
    private Integer flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
        setUpListeners();
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

    private void setUpListeners() {
        binding.cardFilter.setOnClickListener(v -> binding.flFilter.setVisibility(View.VISIBLE));
        binding.cardFrom.setOnClickListener(v -> showDateDialog(1));
        binding.cardTo.setOnClickListener(v -> showDateDialog(2));
        binding.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.flFilter.setVisibility(View.GONE);
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.flFilter.setVisibility(View.GONE);
            }
        });
    }

    private void showDateDialog(Integer flag) {
        this.flag = flag;
        datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        if (flag == 1) {
            FromDate = dateFormat.format(new Date(calendar.getTimeInMillis()));
            binding.tvFrom.setText(FromDate);
        } else if (flag == 2) {
            ToDate = dateFormat.format(new Date(calendar.getTimeInMillis()));
            binding.tvTo.setText(ToDate);
        }

    }

}