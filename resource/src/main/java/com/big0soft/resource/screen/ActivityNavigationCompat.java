package com.big0soft.resource.screen;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.big0soft.resource.data.SharedPreferenceDao;
import com.big0soft.resource.helper.FragmentsHelpers;
import com.big0soft.resource.helper.NavigationUtils;

public abstract class ActivityNavigationCompat extends BaseActivityCompat implements FragmentNavigationController {


    /**
     * custom back pressed button
     * any fragment implements {@link OnBackPressedListener}
     * and click on back button
     * will call {@link OnBackPressedListener#onBackPressed()}
     */
    @Override
    public void onBackPressed() {
        final Fragment currentFragment = NavigationUtils.currentFragment(navHostFragment());
        if (currentFragment==null)
            super.onBackPressed();
        if (currentFragment instanceof OnBackPressedListener)
            ((OnBackPressedListener) currentFragment).onBackPressed();
        else if (!controller().popBackStack())
            finish();
    }

    public SharedPreferenceDao sharedPreference() {
        return FragmentsHelpers.sharedPreference(this);
    }

    public interface OnBackPressedListener {

        void onBackPressed();
    }
}
