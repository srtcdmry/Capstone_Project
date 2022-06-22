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
import com.dgpays.myapplication.holder.BagHolder;
import com.dgpays.myapplication.model.MyBagResources;
import com.dgpays.myapplication.model.ProductResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BagAdapter extends RecyclerView.Adapter<BagHolder> {

    private List<ProductResource> productResources;
    private List<MyBagResources.Product> myBagResources;    TotalAmountListener totalAmountListener;
    Context context;
    private int lastSelectedPosition = -1;
    private  double totalAmount = 0 ;
    private int quantity;
    private double price;
    private int selectedPos = RecyclerView.NO_POSITION;



    public BagAdapter(Context context, List<MyBagResources.Product> myBagResources, List<ProductResource> productResources, TotalAmountListener totalAmountListener) {
        this.productResources = productResources;
        this.myBagResources = myBagResources;
        this.context = context;
        this.totalAmountListener = totalAmountListener;
    }

    @NonNull
    @Override
    public BagHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BagAdapterBinding bagAdapterBinding = BagAdapterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BagHolder(bagAdapterBinding, totalAmountListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BagHolder holder, int position) {
        MyBagResources.Product myBagResource = myBagResources.get(position);
        ProductResource productResource = productResources.get(myBagResource.getProductId());
        String productId = myBagResource.getProductId().toString();
        String title = myBagResource.getProductId().toString();
        String image = productResource.getImage();
        quantity = myBagResource.getQuantity();
        price = productResource.getPrice() * quantity;
        holder.getBinding().title.setText(title);
        holder.getBinding().quantities.setText(String.valueOf(quantity));
        holder.getBinding().price.setText(String.valueOf(price));
        totalAmount = totalAmount + price;
        totalAmountListener.getTotalAmount(totalAmount);
        Glide.with(context)
                .load(image)
                .into(holder.getBinding().imageBagProduct);

        holder.getBinding().buttonAdd.setOnClickListener(view -> {
                selectedPos  = holder.getAdapterPosition();
                MyBagResources.Product myBagRes = myBagResources.get(selectedPos);
                ProductResource productRes = productResources.get(myBagRes.getProductId());
                totalAmount = totalAmount + productRes.getPrice();
                quantity = quantity + 1;
                price = productRes.getPrice() * quantity;
                holder.getBinding().quantities.setText(String.valueOf(quantity));
                holder.getBinding().price.setText(String.valueOf(price));
                totalAmountListener.getTotalAmount(totalAmount);


        });
        
        holder.getBinding().buttonMinus.setOnClickListener(view -> {
            MyBagResources.Product myBagRes = myBagResources.get(holder.getAdapterPosition());
            ProductResource productRes = productResources.get(myBagRes.getProductId());

            if(quantity==0) {
                Toast.makeText(context, "The number of products cannot be less than 0.", Toast.LENGTH_SHORT).show();
            } else {
                totalAmount = totalAmount - productRes.getPrice();
                quantity = quantity - 1;
                price = productRes.getPrice() * quantity;
                holder.getBinding().quantities.setText(String.valueOf(quantity));
                holder.getBinding().price.setText(String.valueOf(price));
                totalAmountListener.getTotalAmount(totalAmount);
            }

        });
    }

    @Override
    public int getItemCount() {
        return myBagResources.size();
    }

}
