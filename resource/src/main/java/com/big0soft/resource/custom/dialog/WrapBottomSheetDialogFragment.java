package com.big0soft.resource.custom.dialog;

import android.app.Application;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.big0soft.resource.screen.ActivityNavigationCompat;
import com.big0soft.resource.screen.OnScreenRequire;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class WrapBottomSheetDialogFragment extends BottomSheetDialogFragment implements OnScreenRequire {
    @Nullable
    private NavController controller;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavHostFragment navHostFragment = ((ActivityNavigationCompat) requireActivity()).navHostFragment();
        controller = navHostFragment.getNavController();
    }

    @Nullable
    public NavController controller() {
        return controller;
    }

    @Override
    public Application application() {
        return requireActivity().getApplication();
    }


}
