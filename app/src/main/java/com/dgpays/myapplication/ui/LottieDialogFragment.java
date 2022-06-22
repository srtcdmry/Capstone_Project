package com.dgpays.myapplication.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.dgpays.myapplication.databinding.LottieDialogBinding;

public class LottieDialogFragment extends Dialog {

    private LottieDialogBinding binding;

    public LottieDialogFragment(@NonNull Context context) {
        super(context);
        binding = LottieDialogBinding.inflate(getLayoutInflater());
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();

        wlmp.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wlmp);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(binding.getRoot());

        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
    }
}
