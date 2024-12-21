package com.big0soft.nearexpireadmin.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.data.base.CustomeBaseFragment;
import com.big0soft.nearexpireadmin.databinding.FragmentVerificationBinding;

import io.reactivex.rxjava3.annotations.NonNull;

public class VerificationFragment extends CustomeBaseFragment<FragmentVerificationBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verification;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, NavController controller) {
        super.onViewCreated(view, savedInstanceState, controller);
    }


}