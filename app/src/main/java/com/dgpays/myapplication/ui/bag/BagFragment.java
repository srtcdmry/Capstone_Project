package com.dgpays.myapplication.ui.bag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.dgpays.myapplication.NotificationWorker;
import com.dgpays.myapplication.PaymentSuccessActivity;
import com.dgpays.myapplication.TotalAmountListener;
import com.dgpays.myapplication.adapter.BagAdapter;
import com.dgpays.myapplication.databinding.FragmentBagBinding;
import com.dgpays.myapplication.retrofit.ApiRetrofitClient;
import com.dgpays.myapplication.retrofit.ProductResourcesInterface;
import com.dgpays.myapplication.ui.LottieDialogFragment;


public class BagFragment extends Fragment implements View.OnClickListener, TotalAmountListener, LifecycleObserver {
    public static final String MESSAGE_STATUS = "message_status";
    private FragmentBagBinding binding;
    ProductResourcesInterface apiRetrofitClient;
    private int userId = 1;
    BagAdapter bagAdapter;
    BagViewModel bagViewModel;
    double totalAmount = 0;
    double totals = 0;
    LottieDialogFragment lottieDialogFragment;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBagBinding.inflate(inflater, container, false);
        apiRetrofitClient = ApiRetrofitClient.getClient().create(ProductResourcesInterface.class);
        lottieDialogFragment = new LottieDialogFragment(getActivity());
        lottieDialogFragment.show();
        getMyBag2();
        View root = binding.getRoot();
        binding.setListener(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onAppBackgrounded() {
        if(bagAdapter.getItemCount() != 0) {
            NotificationWorker.periodicRequest();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onAppForegrounded() {
        NotificationWorker.cancelPeriodicRequest();
    }

    public void getMyBag2() {
        bagViewModel = new ViewModelProvider(this).get(BagViewModel.class);
        bagViewModel.getOneProductResourceLiveData().observe(getActivity(), getMyBagOneProduct -> {
            if (getMyBagOneProduct != null) {
                bagViewModel.myBagResourceLiveData().observe(getActivity(), getMyBag -> {
                    if (getMyBag != null) {
                        lottieDialogFragment.dismiss();
                        for (int i = 0; i < getMyBag.get(2).getProducts().size(); i++) {
                            totalAmount += getMyBagOneProduct.get(getMyBag.get(2).getProducts().get(i).getProductId()).getPrice();
                        }
                        binding.totalAmount.setText(String.valueOf(totalAmount));
                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                        binding.bagRecycler.setLayoutManager(layoutManager);
                        bagAdapter = new BagAdapter(getContext(), getMyBag.get(2).getProducts(), bagViewModel.getOneProductResourceLiveData().getValue(), this);
                        binding.bagRecycler.setAdapter(bagAdapter);
                    } else {
                        binding.totalAmount.setText("0.00");
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == binding.button) {
            Intent intent = new Intent(getActivity(), PaymentSuccessActivity.class);
            startActivity(intent);
        } else if(view == binding.buttonPromotion) {
        }
    }

    @Override
    public void getTotalAmount(double totalAmount) {
        totals = totalAmount;
        binding.totalAmount.setText(String.valueOf(totals));
    }
}