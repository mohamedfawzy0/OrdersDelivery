package com.ordersdelivery.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ordersdelivery.R;
import com.ordersdelivery.databinding.ItemOrderBinding;
import com.ordersdelivery.domain.model.response.OrdersModel;

import java.util.List;

import javax.inject.Inject;


public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OrdersModel.Data.DeliveryBill> list;

    private Context context;
    private LayoutInflater inflater;
    private Fragment fragment;
    private String lang;


    @Inject
    public OrderAdapter(Context context, Fragment fragment,String lang) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.fragment = fragment;
        this.lang=lang;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_order, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.binding.setModel(list.get(position));
        myHolder.binding.setLang(lang);
        Double price = Double.parseDouble(myHolder.binding.getModel().getBILL_AMT());
        Long total = Math.round(price);
        myHolder.binding.tvPrice.setText(total + " " + context.getResources().getString(R.string.l_e));
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public void updateList(List<OrdersModel.Data.DeliveryBill> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public ItemOrderBinding binding;

        public MyHolder(ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
