package com.big0soft.nearexpireadmin.ui.fragment;

import static com.big0soft.resource.utils.StrUtils.isEmpty;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.data.base.CustomeBaseFragment;
import com.big0soft.nearexpireadmin.data.requests.LoginRequest;
import com.big0soft.nearexpireadmin.databinding.FragmentLoginBinding;
import com.big0soft.nearexpireadmin.databinding.FragmentPreLoginBinding;
import com.big0soft.nearexpireadmin.domain.enums.AuthProvider;
import com.big0soft.nearexpireadmin.ui.activities.AuthActivity;
import com.big0soft.nearexpireadmin.ui.viewmodel.LoginViewModel;
import com.big0soft.resource.ui.KeyboardUtils;

import io.reactivex.rxjava3.annotations.NonNull;

public class LoginFragment extends CustomeBaseFragment<FragmentLoginBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    private LoginViewModel mLoginViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, NavController controller) {
        super.onViewCreated(view, savedInstanceState, controller);
        mLoginViewModel = ((AuthActivity) activityNavigationCompat()).loginViewModel();
        mLoginViewModel.getErrorResponseLiveData().observe(getViewLifecycleOwner(), errorResponse -> {
            if (errorResponse == null) return;
            if (isEmpty(errorResponse.getMessage())) {
                return;
            }
            snackbar(errorResponse.getMessage())
                    .show();
        });

        getBinding().fragmentLoginSignUpBtn.setOnClickListener(v -> {
            controller.navigate(R.id.action_fragmentLogin2_to_fragmentRegistration2);
        });
        getBinding().fragmentRegisterButtonSignIn.setOnClickListener(v -> {
            String email = getBinding().fragmentLoginEmailEt.getText().toString();
            String password = getBinding().fragmentLoginEditTextPassword.getText().toString();
            mLoginViewModel.login(new LoginRequest(email, password, AuthProvider.EMAIL));
            KeyboardUtils.hideKeyboard(requireActivity());

        });


        getBinding().setLoginViewModel(mLoginViewModel);
        getBinding().setLifecycleOwner(this);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}