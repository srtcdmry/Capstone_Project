package com.dgpays.myapplication.holder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dgpays.myapplication.TotalAmountListener;
import com.dgpays.myapplication.databinding.ShopAdapterBinding;

public class ShopHolder extends RecyclerView.ViewHolder {

    private ShopAdapterBinding binding;

    public ShopHolder(@NonNull ShopAdapterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ShopAdapterBinding getBinding() {
        return binding;
    }

    public void setBinding(ShopAdapterBinding binding) {
        this.binding = binding;
    }
}