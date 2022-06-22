package com.dgpays.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dgpays.myapplication.TotalAmountListener;
import com.dgpays.myapplication.databinding.BagAdapterBinding;
import com.dgpays.myapplication.databinding.ShopAdapterBinding;
import com.dgpays.myapplication.holder.BagHolder;
import com.dgpays.myapplication.holder.ShopHolder;
import com.dgpays.myapplication.model.MyBagResources;
import com.dgpays.myapplication.model.ProductResource;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopHolder> {

    private List<String> categoryResource;

    private Context context;


    public ShopAdapter(Context context, List<String> categoryResource) {
        this.context = context;
        this.categoryResource = categoryResource;
    }


    @NonNull
    @Override
    public ShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ShopAdapterBinding shopAdapterBinding = ShopAdapterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ShopHolder(shopAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopHolder holder, int position) {

        String category = categoryResource.get(position);
        holder.getBinding().textCategory.setText(category);
    }

    @Override
    public int getItemCount() {
        return categoryResource.size();
    }
}