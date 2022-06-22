package com.dgpays.myapplication.holder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dgpays.myapplication.databinding.ProductAdapterBinding;

public class ProductHolder extends RecyclerView.ViewHolder {

    private ProductAdapterBinding binding;

    public ProductHolder(@NonNull ProductAdapterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ProductAdapterBinding getBinding() {
        return binding;
    }

    public void setBinding(ProductAdapterBinding binding) {
        this.binding = binding;
    }
}