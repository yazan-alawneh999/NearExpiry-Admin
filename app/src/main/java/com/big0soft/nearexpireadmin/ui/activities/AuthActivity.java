package com.big0soft.nearexpireadmin.ui.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.data.api.RetrofitConfig;
import com.big0soft.nearexpireadmin.data.repository.remote.LoginRepository;
import com.big0soft.nearexpireadmin.databinding.ActivityAuthBinding;
import com.big0soft.nearexpireadmin.ui.viewmodel.LoginViewModel;
import com.big0soft.resource.helper.IntentHelper;
import com.big0soft.resource.screen.ActivityNavigationCompat;
import com.big0soft.resource.session.SessionManagerImpl;
import com.big0soft.resource.utils.ViewModelUtils;

public class AuthActivity extends ActivityNavigationCompat {
    NavHostFragment navHostFragment;
    NavController navController;
    ActivityAuthBinding mBinding;
    private LoginViewModel mLoginViewModel;


    @Override
    public NavController controller() {
        return navController;
    }


    @Override
    public NavHostFragment navHostFragment() {
        return navHostFragment;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        navHostFragment = mBinding.navHostFragment.getFragment();
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        LoginViewModel.Factory factory = new LoginViewModel.Factory(
                getApplication(),
                new SessionManagerImpl(sharedPreference()),
                new LoginRepository(RetrofitConfig.getInstance().getService())
        );
        mLoginViewModel = ViewModelUtils.instanceViewModel(this, factory, LoginViewModel.class);

        mLoginViewModel.loginBySession();
        mLoginViewModel.getLoginResponseLiveData().observe(this, registrationResponse -> {
            snackbar(getRootView().getResources().getString(R.string.login_successful));
            IntentHelper.startActivityWithFinish(this, MainActivity.class);
        });
        mLoginViewModel.getErrorResponseLiveData().observe(this, errorResponse -> {
            snackbar(errorResponse.getError())
                    .show();
        });





    }


    public LoginViewModel loginViewModel() {
        return mLoginViewModel;
    }

}