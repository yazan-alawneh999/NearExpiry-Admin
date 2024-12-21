package com.big0soft.nearexpireadmin.ui.fragment;

import static com.big0soft.resource.utils.StrUtils.isEmpty;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.data.api.RetrofitConfig;
import com.big0soft.nearexpireadmin.data.base.CustomeBaseFragment;
import com.big0soft.nearexpireadmin.data.repository.remote.RegisterRepository;
import com.big0soft.nearexpireadmin.data.requests.RegistrationRequest;
import com.big0soft.nearexpireadmin.databinding.FragmentRegisterationBinding;
import com.big0soft.nearexpireadmin.domain.enums.AuthProvider;
import com.big0soft.nearexpireadmin.ui.viewmodel.RegisterViewModel;
import com.big0soft.resource.data.BaseSharedPreferenceDao;
import com.big0soft.resource.helper.TAGs;
import com.big0soft.resource.session.SessionManagerImpl;
import com.big0soft.resource.utils.ViewModelUtils;

import io.reactivex.rxjava3.annotations.NonNull;

public class RegisterFragment extends CustomeBaseFragment<FragmentRegisterationBinding> {

    private RegisterViewModel mRegisterViewModel;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_registeration;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, NavController controller) {
        super.onViewCreated(view, savedInstanceState, controller);
        RegisterViewModel.Factory factory = new RegisterViewModel.Factory(
                getApplication(),
                new RegisterRepository(RetrofitConfig.getInstance().getService()),
                new SessionManagerImpl(new BaseSharedPreferenceDao(requireActivity())));
        mRegisterViewModel = ViewModelUtils.instanceViewModel(this, factory, RegisterViewModel.class);
        binding.setRegisterViewModel(mRegisterViewModel);
        binding.setLifecycleOwner(this);

        binding.fragmentRegisterButtonSignupBtn.setOnClickListener(v -> {
            String email = binding.fragmentRegisterEditTextEmail.getText().toString().trim();
            String password = binding.fragmentRegisterEditTextPassword.getText().toString().trim();
            String confirmPassword = binding.fragmentRegisterEditTextConfirmPassword.getText().toString().trim();
            mRegisterViewModel.register(new RegistrationRequest(email, password, AuthProvider.EMAIL), confirmPassword);
        });

        binding.fragmentRegisterButtonSignInBtn.setOnClickListener(v -> {
            controller().navigate(R.id.action_fragmentRegistration2_to_fragmentLogin2);
        });

        mRegisterViewModel.getErrorResponseLiveData().observe(getViewLifecycleOwner(), errorResponse -> {
            if (errorResponse == null) return;
            if (isEmpty(errorResponse.getMessage())) {
                return;
            }
            snackbar(errorResponse.getMessage())
                    .show();
        });
        mRegisterViewModel.getRegisterResponseLiveData().observe(getViewLifecycleOwner(), registrationResponse -> {
            Log.i(TAGs.TAG(getClass()), "onViewCreated: user register: " + registrationResponse);
            String email = binding.fragmentRegisterEditTextEmail.getText().toString().trim();
            snackbar(registrationResponse.getMessage()).show();

        });

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