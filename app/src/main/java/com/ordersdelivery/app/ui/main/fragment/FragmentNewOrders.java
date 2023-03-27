package com.ordersdelivery.app.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ordersdelivery.R;
import com.ordersdelivery.app.adapter.OrderAdapter;
import com.ordersdelivery.app.ui.base.BaseFragment;
import com.ordersdelivery.app.ui.main.MainActivity;
import com.ordersdelivery.app.ui.main.OrdersViewModel;
import com.ordersdelivery.app.utils.Constants;
import com.ordersdelivery.databinding.FragmentNewOrdersBinding;
import com.ordersdelivery.domain.model.request.OrdersValueBody;

import dagger.hilt.android.AndroidEntryPoint;
import io.paperdb.Paper;

@AndroidEntryPoint
public class FragmentNewOrders extends BaseFragment {
    private MainActivity activity;
    private FragmentNewOrdersBinding binding;
    private OrdersViewModel viewModel;
    private OrderAdapter adapter;
    private String lang_no;
    private String user_id;

    public static FragmentNewOrders newInstance(String user_id) {
        FragmentNewOrders fragment = new FragmentNewOrders();
        Bundle args = new Bundle();
        args.putString(Constants.user_id, user_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_orders, container, false);
        user_id = getArguments().getString(Constants.user_id, "");
        initView();
        setUpObservers();
        setUpListeners();
        return binding.getRoot();
    }

    private void initView() {
        if (getLang().equals("en")) {
            lang_no = "2";
        } else {
            lang_no = "1";
        }
        viewModel = new ViewModelProvider(this).get(OrdersViewModel.class);
        viewModel.getOrders(new OrdersValueBody(new OrdersValueBody.Value(user_id, lang_no, "", String.valueOf(Constants.FLAG_NEW))));
        activity = (MainActivity) getActivity();
        Paper.init(activity);
        adapter = new OrderAdapter(activity, this,getLang());
        binding.recView.setLayoutManager(new LinearLayoutManager(activity));
        binding.recView.setAdapter(adapter);
    }

    private void setUpObservers() {
        viewModel.getIsLoading().observe(activity, aBoolean -> {
            binding.swipe.setRefreshing(aBoolean);
        });
        viewModel.getOrdersSuccess().observe(activity, orders -> {
            if (orders.getData() != null) {
                if (orders.getData().getDeliveryBills() != null) {
                    if (orders.getData().getDeliveryBills().size() > 0) {
                        binding.frameNoData.setVisibility(View.GONE);
                        binding.linearNoData.setVisibility(View.GONE);
                        binding.recView.setVisibility(View.VISIBLE);
                        if (adapter != null) {
                            adapter.updateList(orders.getData().getDeliveryBills());
                        } else {

                        }
                    } else {
                        binding.frameNoData.setVisibility(View.VISIBLE);
                        binding.linearNoData.setVisibility(View.VISIBLE);
                        binding.recView.setVisibility(View.GONE);
                    }
                }

            } else if (orders.getData() == null && orders.getResult().getErrNo() != 0) {

                Toast.makeText(activity, orders.getResult().getErrMsg(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUpListeners() {
        binding.swipe.setOnRefreshListener(() -> {
            viewModel.getOrders(new OrdersValueBody(new OrdersValueBody.Value(user_id, lang_no, "", "0")));
        });
    }
}