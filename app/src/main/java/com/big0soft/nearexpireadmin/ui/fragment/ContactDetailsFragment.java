package com.big0soft.nearexpireadmin.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.data.base.CustomeBaseFragment;
import com.big0soft.nearexpireadmin.databinding.FragmentContactDetailsBinding;

import io.reactivex.rxjava3.annotations.NonNull;

public class ContactDetailsFragment extends CustomeBaseFragment<FragmentContactDetailsBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact_details;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, NavController controller) {
        super.onViewCreated(view, savedInstanceState, controller);
        getBinding().fragmentContactDetailsDetailsSubmitBtn.setOnClickListener(v->controller().navigate(R.id.action_contactDetailsFragment_to_verificationFragment));
    }



}