package com.big0soft.nearexpireadmin.ui.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.databinding.ActivityStoreRegistrationBinding;
import com.big0soft.resource.screen.ActivityNavigationCompat;

public class StoreRegistrationActivity extends ActivityNavigationCompat {
    private NavController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ActivityStoreRegistrationBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_store_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NavHostFragment storeRegistrationNavHostFragment = mBinding.fragmentContainerView.getFragment();
        assert storeRegistrationNavHostFragment != null;
        controller = storeRegistrationNavHostFragment.getNavController();

        setSupportActionBar(mBinding.activityRegistrationStoreToolbar);
        mBinding.ActivityRegistrationsStepper.setupWithNavController(controller);


        AppBarConfiguration configuration = new AppBarConfiguration.Builder(R.id.fragmentStoreSetup2).build();
        NavigationUI.setupActionBarWithNavController(this, controller, configuration);
        NavigationUI.setupWithNavController(mBinding.activityRegistrationStoreToolbar, controller, configuration);


    }


    @Override
    public NavController controller() {
        return controller;
    }

    @Override
    public NavHostFragment navHostFragment() {
        return navHostFragment();
    }
}