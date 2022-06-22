package com.dgpays.myapplication.holder;

import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dgpays.myapplication.TotalAmountListener;
import com.dgpays.myapplication.databinding.BagAdapterBinding;

public class BagHolder extends RecyclerView.ViewHolder {

    private BagAdapterBinding binding;
    private TotalAmountListener totalAmountListener;

    public BagHolder(@NonNull BagAdapterBinding binding, TotalAmountListener totalAmountListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.totalAmountListener = totalAmountListener;
    }

    public BagAdapterBinding getBinding() {
        return binding;
    }

    public void setBinding(BagAdapterBinding binding) {
        this.binding = binding;
    }
}