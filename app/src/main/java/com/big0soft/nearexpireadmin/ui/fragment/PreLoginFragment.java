package com.big0soft.nearexpireadmin.ui.fragment;

import android.os.Bundle;

import android.view.View;

import androidx.annotation.Nullable;

import androidx.navigation.NavController;


import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.data.base.CustomeBaseFragment;
import com.big0soft.nearexpireadmin.databinding.FragmentPreLoginBinding;

import io.reactivex.rxjava3.annotations.NonNull;

public class PreLoginFragment extends CustomeBaseFragment<FragmentPreLoginBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pre_login;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, NavController controller) {
        super.onViewCreated(view, savedInstanceState, controller);
    }



}