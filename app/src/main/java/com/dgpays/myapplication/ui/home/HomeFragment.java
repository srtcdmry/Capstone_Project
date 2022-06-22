package com.dgpays.myapplication.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.dgpays.myapplication.adapter.ProductAdapter;
import com.dgpays.myapplication.databinding.FragmentHomeBinding;
import com.dgpays.myapplication.retrofit.ApiRetrofitClient;
import com.dgpays.myapplication.retrofit.ProductResourcesInterface;
import com.dgpays.myapplication.ui.LottieDialogFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    HomeViewModel homeViewModel;
    ProductResourcesInterface apiRetrofitClient;
    ProductAdapter productAdapter;
    Runnable mTicker;
    private int i = 0;
    private boolean stop = false;

    private LottieDialogFragment lottieDialogFragment;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        apiRetrofitClient = ApiRetrofitClient.getClient().create(ProductResourcesInterface.class);
        lottieDialogFragment = new LottieDialogFragment(getActivity());
        lottieDialogFragment.show();
        getAllProducts();
        getOneProduct();
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getAllProducts() {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getProductResourceLiveData().observe(getActivity(), playlistResponse -> {
            if (playlistResponse != null) {
                lottieDialogFragment.dismiss();
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.productsRecycler.setLayoutManager(layoutManager);
                productAdapter = new ProductAdapter(getContext(), playlistResponse);
                binding.productsRecycler.setAdapter(productAdapter);
            }
        });
    }

    public void getOneProduct() {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getOneProductResourceLiveData().observe(getActivity(), getOneProductList -> {
            if (getOneProductList != null) {

                Handler mHandler = new Handler();
                mTicker = new Runnable() {
                    @Override
                    public void run() {
                        if(!stop) {
                            if (i == getOneProductList.size()) {
                                i = 0;
                            } else {
                                binding.textView2.setText(getOneProductList.get(i).getCategory().toUpperCase());
                                Glide.with(getActivity())
                                        .load(getOneProductList.get(i).getImage())
                                        .into(binding.imageView);
                                i++;
                            }
                            mHandler.postDelayed(mTicker,5000);
                        }
                    }
                };
                mTicker.run();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        stop=true;
    }

    @Override
    public void onResume() {
        super.onResume();
        stop=false;
        getOneProduct();
        getAllProducts();
    }
}