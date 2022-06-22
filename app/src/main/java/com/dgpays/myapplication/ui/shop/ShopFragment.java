package com.dgpays.myapplication.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dgpays.myapplication.adapter.ShopAdapter;
import com.dgpays.myapplication.databinding.FragmentShopBinding;
import com.dgpays.myapplication.retrofit.ApiRetrofitClient;
import com.dgpays.myapplication.retrofit.ProductResourcesInterface;
import com.dgpays.myapplication.ui.LottieDialogFragment;

import java.util.ArrayList;
import java.util.List;


public class ShopFragment extends Fragment implements View.OnClickListener {

    ProductResourcesInterface apiRetrofitClient;
    private FragmentShopBinding binding;
    ShopViewModel shopViewModel;
    ShopAdapter shopAdapter;
    LottieDialogFragment lottieDialogFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentShopBinding.inflate(inflater, container, false);
        apiRetrofitClient = ApiRetrofitClient.getClient().create(ProductResourcesInterface.class);
        binding.setListener(this::onClick);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void getCategories(){
        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.getCategoriesLiveData().observe(getActivity(), getCategoriesList ->{
            if(getCategoriesList != null) {
                binding.textView3.setVisibility(View.VISIBLE);
                lottieDialogFragment.dismiss();
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                binding.shopRecycler.setLayoutManager(layoutManager);
                binding.shopRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                shopAdapter = new ShopAdapter(getContext(), getCategoriesList);
                binding.shopRecycler.setAdapter(shopAdapter);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == binding.getItemsBtn) {
            lottieDialogFragment = new LottieDialogFragment(getActivity());
            lottieDialogFragment.show();
            getCategories();
        }

    }
}