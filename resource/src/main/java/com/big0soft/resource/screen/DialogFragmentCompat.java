package com.big0soft.resource.screen;

import android.app.Application;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.big0soft.resource.data.SharedPreferenceDao;
import com.big0soft.resource.helper.FragmentsHelpers;
import com.google.android.material.snackbar.Snackbar;

public class DialogFragmentCompat extends DialogFragment implements FragmentNavigationController {
    /**
     * @return singleton object from shared preference
     * @see FragmentsHelpers
     */
    public SharedPreferenceDao sharedPreference() {
        return FragmentsHelpers.sharedPreference(requireContext());
    }

    /**
     * @return controller instance
     */
    @Override
    public NavController controller() {
        return activityNavigationCompat().controller();
    }

    /**
     * @param <T> type of activity should extend from {@link ActivityNavigationCompat} and implements {@link FragmentNavigationController}
     * @return activity instance
     * @throws ClassCastException if activity not extend from {@link ActivityNavigationCompat} or implements {@link FragmentNavigationController}
     */
    public <T extends ActivityNavigationCompat & FragmentNavigationController> T activityNavigationCompat() throws ClassCastException {
        if (!(requireActivity() instanceof ActivityNavigationCompat)) {
            throw new ClassCastException(requireActivity().getIntent().getComponent().getClassName() + " activity it not extend from ActivityNavigationCompat");
        }
        if (!(requireActivity() instanceof FragmentNavigationController)) {
            throw new ClassCastException(requireActivity().getIntent().getComponent().getClassName() + " activity it not extend from FragmentNavigationController");
        }
        return (T) requireActivity();
    }
    public void removeDialogBackground() {
        if (requireDialog().getWindow() == null) return;
        requireDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(com.intuit.sdp.R.dimen._100sdp));
        requireDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }
    @Override
    public NavHostFragment navHostFragment() {
        return activityNavigationCompat().navHostFragment();
    }

    public <T extends Application> T application() {
        return ((T) requireActivity().getApplication());
    }

    public Snackbar snackbar(Object message) {
        return activityNavigationCompat().snackbar(message);
    }

}
