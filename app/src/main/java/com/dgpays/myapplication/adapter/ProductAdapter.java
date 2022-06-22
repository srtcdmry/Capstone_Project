package com.dgpays.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dgpays.myapplication.databinding.ProductAdapterBinding;
import com.dgpays.myapplication.holder.ProductHolder;
import com.dgpays.myapplication.model.ProductResource;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductHolder> {

        private List<ProductResource> productResources;
        private Context context;

        public ProductAdapter(Context context, List<ProductResource> productResources) {
                this.context = context;
                this.productResources = productResources;
        }

        public void setData(List<ProductResource> productResources){
                this.productResources = productResources;
                notifyDataSetChanged();
        }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductAdapterBinding productAdapterBinding = ProductAdapterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductHolder(productAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
            ProductResource productResource = productResources.get(position);
            String categories = productResource.getCategory();
            String title = productResource.getTitle();
            Double price = productResource.getPrice();
            holder.getBinding().categories.setText(categories);
            holder.getBinding().title.setText(title);
            holder.getBinding().price.setText(String.valueOf(price));

            Glide.with(context)
                    .load(productResource.getImage())
                    .into(holder.getBinding().imageView);
    }

    @Override
    public int getItemCount() {
        return productResources.size();
    }
}